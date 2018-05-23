package com.pachiraframework.scheduler.service.impl;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.entity.BaseEntity.IsDeletedEnum;
import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobManager;
import com.pachiraframework.scheduler.dao.JobDao;
import com.pachiraframework.scheduler.dto.AddJob;
import com.pachiraframework.scheduler.dto.SearchJobCriteria;
import com.pachiraframework.scheduler.entity.Job;
import com.pachiraframework.scheduler.service.JobService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private ZookeeperJobManager zookeeperJobManager;

	@Override
	public Page<Job> search(SearchJobCriteria criteria) {
		WrappedPageRequest pageRequest = new WrappedPageRequest(criteria);
		return jobDao.findByPage(pageRequest);
	}

	@Override
	public ExecuteResult<Job> add(AddJob addJob) {
		try {
			new CronExpression(addJob.getCron());
		}catch(Exception e) {
			return ExecuteResult.newErrorResult("非法的cron表达式");
		}
		Job job = new Job();
		job.setName(addJob.getName());
		job.setDescription(addJob.getDescription());
		job.setCron(addJob.getCron());
		job.setInterfaceName(addJob.getInterfaceName());
		job.setMethod(addJob.getMethod());
		job.setTimeout(addJob.getTimeout());
		job.setType(addJob.getType());
		job.setIsDeleted(IsDeletedEnum.NOT_DELTED.getIsDeleted());
		job.setRegistry(addJob.getRegistry());
		jobDao.insert(job);
		log.info("Added new Job ,id={},name={},cron={}",job.getId(),job.getName(),job.getCron());
		zookeeperJobManager.add(job);
		return ExecuteResult.newSuccessResult(job);
	}

	@Override
	public ExecuteResult<Job> delete(Long jobId) {
		Job job = jobDao.getById(jobId);
		jobDao.delete(jobId);
		zookeeperJobManager.delete(jobId);
		log.info("删除job id={}",jobId);
		return ExecuteResult.newSuccessResult(job);
	}

}

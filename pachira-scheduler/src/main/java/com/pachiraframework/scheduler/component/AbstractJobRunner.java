package com.pachiraframework.scheduler.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Throwables;
import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobElector;
import com.pachiraframework.scheduler.dao.JobHistoryDao;
import com.pachiraframework.scheduler.entity.Job;
import com.pachiraframework.scheduler.entity.JobHistory;
import com.pachiraframework.scheduler.entity.JobHistory.StatusEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Job运行器
 * @author kevin
 *
 */
@Slf4j
public abstract class AbstractJobRunner {
	@Autowired
	private JobHistoryDao jobHistoryDao;
	@Autowired
	private ZookeeperJobElector zookeeperJobElector;
	/**
	 * 执行Job
	 * @param job
	 * @return
	 */
	public void run(Job job) {
		if(this.canRun(job)) {
			JobHistory history = beforeRun(job);
			try {
				this.runInternel(job);
				afterRun(job,history);
			}catch(Exception e) {
				log.error("Exceptions occurs when execute job,id={},name={},cron={},exception:\n{}",job.getId(),job.getName(),job.getCron(),Throwables.getStackTraceAsString(e));
				afterThrows(job,history, e);
			}
		}
	}
	/**
	 * 实际要执行的任务逻辑
	 * @param job
	 */
	protected abstract void runInternel(Job job) throws Exception;
	protected JobHistory beforeRun(Job job) {
		JobHistory history = new JobHistory();
		history.setJobId(job.getId());
		history.setJobName(job.getName());
		history.setCron(job.getCron());
		history.setStartedAt(new Date());
		history.setStatus(StatusEnum.ING.toString());
		history.setInstance(this.zookeeperJobElector.getInstance());
		jobHistoryDao.insert(history);
		log.info("fefore run job {},history id {}",job.getId(),history.getId());
		return history;
	}
	
	protected void afterRun(Job job,JobHistory history) {
		history.setEndedAt(new Date());
		jobHistoryDao.markSuccess(history.getId(),history.getEndedAt());
		log.info("after run job {}",job.getId());
	}
	
	protected void afterThrows(Job job,JobHistory history,Exception e) {
		String message = e.getMessage();
		message = message.length()>200?message.substring(0, 200):message;
		jobHistoryDao.markFaild(history.getId(),new Date(), message);
		log.info("after throws run job {},message:\n",job.getId(),Throwables.getStackTraceAsString(e));
	}
	/**
	 * 当前job在当前节点上是否具有运行资格
	 * @param job
	 * @return
	 */
	protected boolean canRun(Job job) {
		return this.zookeeperJobElector.isLeader(job.getId());
		
	}
}

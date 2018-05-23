package com.pachiraframework.scheduler.component;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobManager;
import com.pachiraframework.scheduler.dao.JobDao;
import com.pachiraframework.scheduler.entity.Job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class JobScheduler implements InitializingBean,DisposableBean {
	@Autowired
	private JobRunnerFactory jobRunnerFactory;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private ZookeeperJobManager zookeeperJobManager;
	@Autowired
	private Scheduler scheduler;


	/**
	 * 添加并调度一个新的任务
	 * 
	 * @param job
	 * @return 添加成功，返回true,任务已经存在时，返回false
	 */
	@SneakyThrows
	public void addJob(Job job) {
		JobKey jobKey = new JobKey(""+job.getId());
		JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
		if(jobDetail == null) {
			jobDetail = newJob(SimpleJob.class).withIdentity(jobKey).build();
			jobDetail.getJobDataMap().put(SimpleJob.JOB_KEY, job);
			jobDetail.getJobDataMap().put(SimpleJob.JOB_RUNNER_FACTORY,this.jobRunnerFactory);

			TriggerKey triggerKey = new TriggerKey("" + job.getId());
			Trigger trigger = newTrigger().withIdentity(triggerKey)
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).startNow().build();
			scheduler.scheduleJob(jobDetail, trigger);
		}
	}
	
	/**
	 * 从调度器中删除job
	 * @param jobId
	 * @return
	 */
	@SneakyThrows
	public void removeJob(Long jobId) {
		JobKey jobKey = new JobKey(""+jobId);
		this.scheduler.deleteJob(jobKey);
		log.info("removed task {} from scheduler.",jobId);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		scheduler.start();
		Set<String> set = Sets.newHashSet();
		List<Job> jobs = jobDao.getAll();
		for (Job job : jobs) {
			zookeeperJobManager.add(job);
			set.add(String.valueOf(job.getId()));
		}
	}

	@Override
	public void destroy() throws Exception {
		scheduler.shutdown();
	}
}

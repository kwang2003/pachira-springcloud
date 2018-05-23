package com.pachiraframework.scheduler.component;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pachiraframework.scheduler.entity.Job;

/**
 * @author wangxuzheng
 *
 */
public class SimpleJob implements org.quartz.Job{
	public static final String JOB_KEY = "job";
	public static final String JOB_RUNNER_FACTORY = "jobRunnerFactory";
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		Job job = (Job)jobExecutionContext.getMergedJobDataMap().get(JOB_KEY);
		JobRunnerFactory jobRunnerFactory = (JobRunnerFactory)jobExecutionContext.getMergedJobDataMap().get(JOB_RUNNER_FACTORY);
		AbstractJobRunner jobRunner = jobRunnerFactory.getJobRunner(job);
		JobRunnerContext context = new JobRunnerContext(jobRunner);
		context.run(job);
	}
}

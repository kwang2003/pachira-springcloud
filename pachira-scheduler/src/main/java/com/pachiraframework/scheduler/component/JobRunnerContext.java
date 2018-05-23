package com.pachiraframework.scheduler.component;

import com.pachiraframework.scheduler.entity.Job;

/**
 * @author kevin
 *
 */
public class JobRunnerContext {
	private AbstractJobRunner jobRunner;
	public JobRunnerContext(AbstractJobRunner jobRunner) {
		this.jobRunner = jobRunner;
	}
	public void run(Job job) {
		this.jobRunner.run(job);
	}
}

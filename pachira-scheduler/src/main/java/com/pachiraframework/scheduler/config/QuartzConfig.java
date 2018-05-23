package com.pachiraframework.scheduler.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuzheng
 *
 */
@Configuration
public class QuartzConfig {
	@Bean
	public Scheduler scheduler() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		return scheduler;
	}
}

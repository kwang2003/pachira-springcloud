package com.pachiraframework.scheduler.component;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.scheduler.AbstractSchedulerApplicationTest;
import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobManager;
import com.pachiraframework.scheduler.entity.Job;

/**
 * @author wangxuzheng
 *
 */
public class ZookeeperJobManagerTest extends AbstractSchedulerApplicationTest{
	@Autowired
	private ZookeeperJobManager zookeeperJobManager;
	
	@Before
	public void init() throws Exception {
	}
	
	@After
	public void destroy() throws Exception {
	}
	@Test
	public void testAdd() throws Exception {
		Job job = createJob();
		zookeeperJobManager.add(job);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}
	
	private Job createJob() {
		Job job = new Job();
		job.setCron("0/5 * * * * *");
		job.setName("測試以下");
		job.setDescription("5秒钟执行一次");
		job.setId(1L);
		return job;
	}

}

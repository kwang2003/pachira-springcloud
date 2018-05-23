package com.pachiraframework.scheduler.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobConstants;

public class SubStringTest {
	private static final String JOB_INSTANCE_NODE_REG = "^"+ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR+"\\d{1,}"+ZookeeperJobConstants.PATH_SPLITOR + "\\S*";

	@Test
	public void test() {
		String prefix = ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR;
		String job1Instance1Path = prefix + 1 +ZookeeperJobConstants.PATH_SPLITOR+"instance1";
		assertThat(job1Instance1Path.matches(JOB_INSTANCE_NODE_REG), is(true));
		
		String job1Id = job1Instance1Path.substring(ZookeeperJobConstants.JOB_PATH.length()+1, job1Instance1Path.lastIndexOf(ZookeeperJobConstants.PATH_SPLITOR));
		assertThat(job1Id, equalTo("1"));
		
		String job1Path = prefix + 1;
		assertThat(job1Path.matches(JOB_INSTANCE_NODE_REG), is(false));
	}
}

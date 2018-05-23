package com.pachiraframework.scheduler.component.zookeeper;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pachiraframework.scheduler.entity.Job;

import lombok.SneakyThrows;

/**
 * 基于zookeeper实现的任务选举执行
 * <pre>
 * |--jobs
 *      |--1
 *         |--instance1(临时节点)
 *         |--instance2(临时节点)
 *      |--2
 *         |--instance1(临时节点)
 *         |--instance2(临时节点)
 * </pre>
 * @author wangxuzheng
 *
 */
@Component
public class ZookeeperJobManager{
	@Autowired
	private ZookeeperJobElector jobElector;
	@Autowired
	private CuratorFramework curatorFramework;
	/**
	 * 添加一个job
	 * @param job
	 */
	@SneakyThrows
	public void add(Job job) {
		checkArgument(job.getId()!=null);
		String jobNodePath = jobPath(job.getId());
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(jobNodePath + ZookeeperJobConstants.PATH_SPLITOR + jobElector.getInstance());
		jobElector.electLeader(job.getId());
	}
	
	private String jobPath(Long jobId) {
		return ZookeeperJobConstants.JOB_PATH + ZookeeperJobConstants.PATH_SPLITOR + jobId;
	}
	
	/**
	 * 删除指定ID的job
	 * @param id
	 */
	@SneakyThrows
	public void delete(Long id) {
		String jobNodePath = jobPath(id);
		curatorFramework.delete().deletingChildrenIfNeeded().forPath(jobNodePath);
	}
}

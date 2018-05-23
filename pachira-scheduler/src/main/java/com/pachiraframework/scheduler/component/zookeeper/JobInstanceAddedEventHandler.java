package com.pachiraframework.scheduler.component.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pachiraframework.scheduler.component.JobScheduler;
import com.pachiraframework.scheduler.dao.JobDao;
import com.pachiraframework.scheduler.entity.Job;

import lombok.extern.slf4j.Slf4j;

/**
 * ZookeeperJobConstants.JOB_INSTANCES_PATH 下 节点发生增加
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class JobInstanceAddedEventHandler extends AbstractZookeeperEventHandler {
	private static final String JOB_INSTANCE_NODE_REG = "^"+ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR+"\\d{1,}"+ZookeeperJobConstants.PATH_SPLITOR + "\\S*";
	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobScheduler jobScheduler;
	@Autowired
	private ZookeeperJobElector jobElector;
	@Autowired
	private CuratorFramework curatorFramework;
	@Override
	protected boolean match(TreeCacheEvent event) {
		String path = event.getData().getPath();
		Type eventType = event.getType();
		if(Type.NODE_ADDED.equals(eventType) && path.matches(JOB_INSTANCE_NODE_REG)) {
			return true;
		}
		return false;
	}

	@Override
	protected void handleInternal(TreeCacheEvent event) throws Exception {
		String path = event.getData().getPath();
		String jobId = path.substring(ZookeeperJobConstants.JOB_PATH.length() + 1, path.lastIndexOf(ZookeeperJobConstants.PATH_SPLITOR));
		Long id = Long.valueOf(jobId);
		String jobNodePath = jobPath(id);
		String instancePath = jobNodePath + ZookeeperJobConstants.PATH_SPLITOR + jobElector.getInstance();
		Stat stat = curatorFramework.checkExists().forPath(instancePath);
		if(stat == null) {
			curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(instancePath);
		}
		Job job = jobDao.getById(id);
		if(job == null) {
			log.warn("找不到job id={}的任务信息，无法加入到调度器中",jobId);
			return;
		}
		jobScheduler.addJob(job);
		log.info("找到job id={}的任务信息，加入到任务调度器中（如果没有在调度器中）",jobId);
	}
	private String jobPath(Long jobId) {
		return ZookeeperJobConstants.JOB_PATH + ZookeeperJobConstants.PATH_SPLITOR + jobId;
	}
}

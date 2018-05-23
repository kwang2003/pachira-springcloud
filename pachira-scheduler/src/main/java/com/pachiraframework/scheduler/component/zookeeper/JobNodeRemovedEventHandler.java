package com.pachiraframework.scheduler.component.zookeeper;

import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pachiraframework.scheduler.component.JobScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class JobNodeRemovedEventHandler extends AbstractZookeeperEventHandler {
	private static final String JOB_NODE_REG = "^"+ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR+"\\d{1,}";
	@Autowired
	private JobScheduler jobScheduler;
	@Override
	protected boolean match(TreeCacheEvent event) {
		String path = event.getData().getPath();
		Type eventType = event.getType();
		if(Type.NODE_REMOVED.equals(eventType) && path.matches(JOB_NODE_REG)) {
			return true;
		}
		return false;
	}

	@Override
	protected void handleInternal(TreeCacheEvent event) throws Exception {
		String path = event.getData().getPath();
		String jobId = path.substring(ZookeeperJobConstants.JOB_PATH.length() + 1);
		Long id = Long.valueOf(jobId);
		log.info("job [{}] 节点被移除",jobId);
		jobScheduler.removeJob(id);
	}

}

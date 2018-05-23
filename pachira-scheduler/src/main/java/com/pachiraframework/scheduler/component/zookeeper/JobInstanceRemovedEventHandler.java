package com.pachiraframework.scheduler.component.zookeeper;

import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * ZookeeperJobConstants.JOB_PATH 具体job节点实例发生删除的时候触发
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class JobInstanceRemovedEventHandler extends AbstractZookeeperEventHandler {
	private static final String JOB_INSTANCE_NODE_REG = "^"+ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR+"\\d{1,}"+ZookeeperJobConstants.PATH_SPLITOR + "\\S*";
	@Autowired
	private ZookeeperJobElector zookeeperJobElector;
	@Override
	protected boolean match(TreeCacheEvent event) {
		String path = event.getData().getPath();
		Type eventType = event.getType();
		if(Type.NODE_REMOVED.equals(eventType) && path.matches(JOB_INSTANCE_NODE_REG)) {
			return true;
		}
		return false;
	}

	@Override
	protected void handleInternal(TreeCacheEvent event) throws Exception {
		String path = event.getData().getPath();
		String jobId = path.substring(ZookeeperJobConstants.JOB_PATH.length() + 1, path.lastIndexOf(ZookeeperJobConstants.PATH_SPLITOR));
		Long id = Long.valueOf(jobId);
		log.info("instance[{}] 被移除，从job [{}]节点下将其移除",path,jobId);
		zookeeperJobElector.electLeader(id);
	}

}

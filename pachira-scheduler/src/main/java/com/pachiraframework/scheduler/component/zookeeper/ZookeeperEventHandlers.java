package com.pachiraframework.scheduler.component.zookeeper;

import java.util.List;

import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author wangxuzheng
 *
 */
@Component
public class ZookeeperEventHandlers implements InitializingBean{
	private List<AbstractZookeeperEventHandler> handlers = Lists.newArrayList();
	@Autowired
	private JobInstanceRemovedEventHandler jobInstanceRemovedEventHandler;
	@Autowired
	private JobInstanceAddedEventHandler jobInstanceAddedEventHandler;
	@Autowired
	private JobNodeRemovedEventHandler jobNodeRemovedEventHandler;
	public void handle(TreeCacheEvent event) {
		for(AbstractZookeeperEventHandler handler : this.handlers) {
			handler.handle(event);
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		this.handlers.add(this.jobInstanceRemovedEventHandler);
		this.handlers.add(this.jobInstanceAddedEventHandler);
		this.handlers.add(this.jobNodeRemovedEventHandler);
	}
}

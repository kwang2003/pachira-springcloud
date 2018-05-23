package com.pachiraframework.scheduler.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.RetryForever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.pachiraframework.scheduler.component.zookeeper.ZookeeperEventHandlers;
import com.pachiraframework.scheduler.component.zookeeper.ZookeeperJobConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxuzheng
 *
 */
@Slf4j
@Configuration
public class ZookeeperConfig {
	@Value("${zookeeper.address}")
	private String zkConnectionString;
	@Lazy
	@Autowired
	private ZookeeperEventHandlers zookeeperEventHandlers;

	@Bean(destroyMethod = "close")
	public CuratorFramework curatorFramework() throws Exception {
		CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(zkConnectionString)
				.sessionTimeoutMs(5000).retryPolicy(new RetryForever(2000)).build();
		curator.start();
		
		return curator;
	}
	
	@Bean(initMethod="start",destroyMethod="close")
	public TreeCache watcher(@Autowired CuratorFramework curatorFramework) {
		TreeCache watcher = new TreeCache(curatorFramework, ZookeeperJobConstants.JOB_PATH);
		watcher.getListenable().addListener((client1, event) -> {
			ChildData data = event.getData();
			if (data == null) {
				log.warn("No data in event[" + event + "]");
			} else {
				zookeeperEventHandlers.handle(event);
			}
		});
		return watcher;
	}
}

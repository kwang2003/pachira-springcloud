package com.pachiraframework.scheduler.component.zookeeper;

import org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.zookeeper.KeeperException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 订阅处理zookeeper事件
 * @author wangxuzheng
 *
 */
@Slf4j
public abstract class AbstractZookeeperEventHandler {
	/**
	 * 事件是否匹配当前处理器
	 * @param event
	 * @return
	 */
	protected abstract boolean match(TreeCacheEvent event) ;
	
	/**
	 * 事件处理模版方法
	 * @param event
	 */
	@SneakyThrows
	public void handle(TreeCacheEvent event) {
		if(match(event)) {
			String path = event.getData().getPath();
			Type type = event.getType();
			log.info("事件匹配成功,path={},type={},处理器={}",path,type,this.getClass().getName());
			handleInternal(event);
		}
	}
	
	/**
	 * 处理具体的事件
	 * @param event
	 * @throws KeeperException 
	 */
	protected abstract void handleInternal(TreeCacheEvent event) throws Exception;
}

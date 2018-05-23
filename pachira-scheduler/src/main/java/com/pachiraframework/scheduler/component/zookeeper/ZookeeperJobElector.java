package com.pachiraframework.scheduler.component.zookeeper;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.NetUtils;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * job运行节点选举
 * @author wangxuzheng
 *
 */
@Slf4j
@Component
public class ZookeeperJobElector{
	/**
	 * job运行实例ID
	 */
	@Getter
	private String instance = NetUtils.getLocalHost()+"|"+UUID.randomUUID().toString();
	@Autowired
	private CuratorFramework curatorFramework;
	public void electLeader(Long jobId) throws Exception {
		String path = ZookeeperJobConstants.JOB_PATH+ZookeeperJobConstants.PATH_SPLITOR+jobId;
		Stat pathStat = curatorFramework.checkExists().forPath(path);
		if(pathStat == null) {
			log.warn("[{}] 不存在，无法选举",path);
			return;
		}
		List<String> children = null;
		try {
			children = curatorFramework.getChildren().forPath(path);
		} catch (NoNodeException e) {
			log.warn("节点[{}]已经被删除，job [{}] 的leader被设置为null",path,jobId);
			return;
		}
		if(children.isEmpty()) {
			log.warn("job id={},没有子节点信息",jobId);
			return;
		}
		int length = children.size();
		int index = new Random().nextInt(length);
		String randomLeader = children.get(index);
		curatorFramework.setData().forPath(path, nodeData(randomLeader));
		
		//确保选举出来的leader是合法的
		String leaderPath = path + "/"+randomLeader;
		Stat stat = curatorFramework.checkExists().forPath(leaderPath);
		if(stat == null) {
			electLeader(jobId);
		}
		log.info("Job [{}],leader is {}",jobId,randomLeader);
	}
	private byte[] nodeData(String leader) {
		return leader.getBytes();
	}
	@SneakyThrows
	public String getJobLeader(Long jobId) {
		String path = ZookeeperJobConstants.JOB_PATH+"/"+jobId;
		Stat pathStat = curatorFramework.checkExists().forPath(path);
		if(pathStat == null) {
			log.warn("[{}] 不存在，无法选举",path);
			return null;
		}
		try {
			byte[] data = curatorFramework.getData().forPath(path);
			return new String(data);
		}catch(NoNodeException e) {
			log.warn("节点[{}]已经被删除，job [{}] 的leader被设置为null",path,jobId);
			return null;
		}
	}
	
	/**
	 * 判断当前节点是否是运行指定job的主节点
	 * @param jobId
	 * @return
	 */
	public boolean isLeader(Long jobId) {
		String leader = this.getJobLeader(jobId);
		return this.instance.equals(leader);
	}
}

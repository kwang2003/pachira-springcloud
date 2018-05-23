package com.pachiraframework.scheduler.component.zookeeper;

import lombok.Data;

/**
 * zookeeper上存储的任务节点数据信息
 * @author wangxuzheng
 *
 */
@Data
public class JobNodeData {
	/**
	 * 云新任务的节点示例ID
	 */
	private String leader;
	/**
	 * 任务cron表达式
	 */
	private String cron;
}

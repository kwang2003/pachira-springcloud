package com.pachiraframework.scheduler.dto;

import lombok.Data;

/**
 * @author kevin
 *
 */
@Data
public class AddJob {
	private String name;
	private String cron;
	private String description;
	/**
	 * 任务类型
	 */
	private String type;
	/**
	 * 注册中心地址
	 */
	private String registry;
	/**
	 * 要执行的接口名称
	 */
	private String interfaceName;
	/**
	 * 要执行的方法名
	 */
	private String method;

	/**
	 * 超时时间，单位毫秒
	 */
	private Long timeout;
}

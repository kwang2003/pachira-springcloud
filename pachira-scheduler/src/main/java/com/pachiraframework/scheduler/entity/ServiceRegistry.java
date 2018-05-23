package com.pachiraframework.scheduler.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 服务注册中心
 * @author kevin
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class ServiceRegistry extends BaseEntity<String>{
	private static final long serialVersionUID = -2198588381318693954L;
	private String name;
	private String address;
	private String type;
	
	public static enum TypeEnum{
		/**
		 * Dubbo的zookeeper注册中心
		 */
		DUBBO_ZOOKEEPER
	}
}

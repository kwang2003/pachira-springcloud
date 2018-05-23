package com.pachiraframework.scheduler.service;

import java.util.List;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.scheduler.entity.ServiceRegistry;

/**
 * 服务注册中心接口
 * @author KevinWang
 *
 */
public interface ServiceRegistryService {
	/**
	 * 查询系统中所有的注册中心列表
	 * @return
	 */
	public ExecuteResult<List<ServiceRegistry>> getAll();
}

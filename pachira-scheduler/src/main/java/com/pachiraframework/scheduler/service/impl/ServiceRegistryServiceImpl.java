package com.pachiraframework.scheduler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.scheduler.dao.ServiceRegistryDao;
import com.pachiraframework.scheduler.entity.ServiceRegistry;
import com.pachiraframework.scheduler.service.ServiceRegistryService;

/**
 * @author KevinWang
 *
 */
@Service
public class ServiceRegistryServiceImpl implements ServiceRegistryService {
	@Autowired
	private ServiceRegistryDao serviceRegistryDao;
	@Override
	public ExecuteResult<List<ServiceRegistry>> getAll() {
		return ExecuteResult.newSuccessResult(serviceRegistryDao.getAll());
	}

}

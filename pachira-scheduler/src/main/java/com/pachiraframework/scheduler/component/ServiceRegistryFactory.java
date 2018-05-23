package com.pachiraframework.scheduler.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.RegistryConfig;
import com.google.common.collect.Maps;
import com.pachiraframework.scheduler.dao.ServiceRegistryDao;
import com.pachiraframework.scheduler.entity.ServiceRegistry;
import com.pachiraframework.scheduler.entity.ServiceRegistry.TypeEnum;

/**
 * @author kevin
 *
 */
@Component
public class ServiceRegistryFactory implements InitializingBean,DisposableBean{
	private Map<String, RegistryConfig> dubboRegistry = Maps.newHashMap();
	@Autowired
	private ServiceRegistryDao serviceRegistryDao;
	public RegistryConfig dubboRegistryConfig(String id) {
		return this.dubboRegistry.get(id);
	}
	@Override
	public void destroy() throws Exception {
		RegistryConfig.destroyAll();
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		List<ServiceRegistry> registries = serviceRegistryDao.getAll();
		for(ServiceRegistry registry : registries) {
			TypeEnum type = TypeEnum.valueOf(registry.getType());
			if(TypeEnum.DUBBO_ZOOKEEPER.equals(type)) {
				RegistryConfig config = createDubboRegistry(registry);
				this.dubboRegistry.put(registry.getId(), config);
			}
		}
	}
	
	private RegistryConfig createDubboRegistry(ServiceRegistry registry) {
		RegistryConfig config = new RegistryConfig();
		config.setAddress(registry.getAddress());
		Map<String, Object> map = registry.getPropertiesMap();
		Boolean check = (Boolean) map.get(map.get("check"));
		if(check != null && check) {
			config.setCheck(check);
		}
		return config;
	}
}

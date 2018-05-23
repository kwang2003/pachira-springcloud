package com.pachiraframework.scheduler.component;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.base.Strings;
import com.pachiraframework.scheduler.entity.Job;

/**
 * @author kevin
 *
 */
@Component
public class DubboJobRunner extends AbstractJobRunner {
	@Autowired
	private ServiceRegistryFactory serviceRegistryFactory;
	private ApplicationConfig application = new ApplicationConfig("scheduler");
	@Override
	public void runInternel(Job job) {
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>(); 
		reference.setApplication(application);
		Map<String, Object> map = job.getPropertiesMap();
		// 弱类型接口名
		reference.setInterface(job.getInterfaceName());  
		String version = (String)map.get("version");
		if(!Strings.isNullOrEmpty(version)) {
			reference.setVersion(version);
		}
		String group = (String)map.get("group");
		if(!Strings.isNullOrEmpty(group)) {
			reference.setGroup(group);
		}
		checkArgument(job.getRegistry() != null,"Dubbo 类型的任务必须指定 registry属性");
		RegistryConfig registryConfig = serviceRegistryFactory.dubboRegistryConfig(job.getRegistry());
		checkArgument(registryConfig!=null,"找不到dubbo注册中心 "+job.getRegistry());
		reference.setRegistry(registryConfig);
		// 声明为泛化接口 
		reference.setGeneric(true); 
		// 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用  
		GenericService genericService = reference.get(); 

		// 基本类型以及Date,List,Map等不需要转换，直接调用 
		genericService.$invoke(job.getMethod(), null, null); 
	}

}

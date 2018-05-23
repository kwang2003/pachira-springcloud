package com.pachiraframework.scheduler.component;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.pachiraframework.scheduler.entity.Job;
import com.pachiraframework.scheduler.entity.Job.TypeEnum;

/**
 * @author kevin
 *
 */
@Component
public class JobRunnerFactory implements ApplicationContextAware{
	private Map<TypeEnum, Class<? extends AbstractJobRunner>> map = Maps.newHashMap();
	private ApplicationContext applicationContext;
	{
		map.put(TypeEnum.CONSUL, ConsulJobRunner.class);
		map.put(TypeEnum.HTTP, HttpJobRunner.class);
		map.put(TypeEnum.DUBBO, DubboJobRunner.class);
		map.put(TypeEnum.EUREKA, EurekaJobRunner.class);
	}
	public AbstractJobRunner getJobRunner(Job job){
		TypeEnum type = TypeEnum.valueOf(job.getType());
		checkArgument(type != null,"找不到类型为"+job.getType()+"的任务处理器");
		Class<? extends AbstractJobRunner> requiredType = map.get(type);
		return applicationContext.getBean(requiredType);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}

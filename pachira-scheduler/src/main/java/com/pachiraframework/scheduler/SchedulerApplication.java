package com.pachiraframework.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pachiraframework.scheduler.interceptor.JwtInterceptor;

/**
 * @author wangxuzheng
 *
 */
@EnableScheduling
@SpringBootApplication
public class SchedulerApplication extends WebMvcConfigurerAdapter {
	@Autowired
	private JwtInterceptor jwtInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/job/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}

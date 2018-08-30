package com.pachiraframework.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pachiraframework.util.SnowflakeIdWorker;

/**
 * 公共配置
 * @author KevinWang
 *
 */
@Configuration
public class CommonConfig {
	@Bean
	public SnowflakeIdWorker snowflakeIdWorker() {
		SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 0L);
		return idWorker;
	}
}

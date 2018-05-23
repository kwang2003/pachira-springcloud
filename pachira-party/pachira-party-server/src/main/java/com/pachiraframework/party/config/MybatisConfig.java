package com.pachiraframework.party.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 系统数据源配置
 * @author KevinWang
 *
 */
@Configuration
public class MybatisConfig {
	@Bean("partyDataSource")
	public DataSource partyDataSource(){  
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/pachira_party?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("111111");
		return dataSource;
	}
	@Bean("partySqlSessionFactory")
    public SqlSessionFactory partySqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(partyDataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml")); // 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] mappers = resolver.getResources("classpath*:mybatis/mappers/**/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(mappers);
		return sqlSessionFactoryBean.getObject();
    }
}

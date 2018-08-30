package com.pachiraframework.basic.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 系统数据源配置
 * @author KevinWang
 *
 */
@Configuration
public class MybatisConfig {
	@Bean("basicDataSource")
	public DataSource basicDataSource(){  
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/pachira_basic?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("111111");
		return dataSource;
	}
	@Bean("basicSqlSessionFactory")
    public SqlSessionFactory basicSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(basicDataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml")); // 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] mappers = resolver.getResources("classpath*:mybatis/mappers/**/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(mappers);
		return sqlSessionFactoryBean.getObject();
    }
	
    @Bean("basicTransactionManager")
    public PlatformTransactionManager basicTransactionManager() {  
    	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();  
        transactionManager.setDataSource(basicDataSource());  
        return transactionManager;  
    }  
}

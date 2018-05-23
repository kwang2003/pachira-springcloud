package com.pachiraframework.party.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerAPI
 * 
 * @author KevinWang
 *
 */
@Configuration
@EnableSwagger2 // 启用Swagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {// 创建API基本信息
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.pachiraframework.party")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
		return new ApiInfoBuilder().title("人员、组织、登录用户及权限管理相关API")// API 标题
				.description("用户、角色，登录帐号信息相关API")// API描述
				.contact(new Contact("Kevin Wang", "https://github.com/kwang2003", "12708826@qq.com"))// 联系人
				.version("1.0")// 版本号
				.build();
	}
}

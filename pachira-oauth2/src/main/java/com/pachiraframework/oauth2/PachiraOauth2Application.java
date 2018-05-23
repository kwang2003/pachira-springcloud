package com.pachiraframework.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author KevinWang
 *
 */
@SpringBootApplication
@EnableAuthorizationServer
public class PachiraOauth2Application {
	public static void main(String[] args) {
		SpringApplication.run(PachiraOauth2Application.class, args);
	}
}

package com.pachiraframework.party;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author KevinWang
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PachiraPartyApplication {
	public static void main(String[] args) {
		SpringApplication.run(PachiraPartyApplication.class, args);
	}
}

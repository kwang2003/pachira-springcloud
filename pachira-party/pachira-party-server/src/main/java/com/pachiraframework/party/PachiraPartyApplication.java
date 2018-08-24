package com.pachiraframework.party;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author KevinWang
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PachiraPartyApplication {
	public static void main(String[] args) {
		SpringApplication.run(PachiraPartyApplication.class, args);
	}
}

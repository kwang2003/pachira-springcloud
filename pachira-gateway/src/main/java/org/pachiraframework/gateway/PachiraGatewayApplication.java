package org.pachiraframework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//@EnableResourceServer
public class PachiraGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(PachiraGatewayApplication.class, args);
	}
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //basic proxy
                .route(r -> r.path("/baidu")
                        .uri("http://baidu.com:80/")
                ).build();
    }
}

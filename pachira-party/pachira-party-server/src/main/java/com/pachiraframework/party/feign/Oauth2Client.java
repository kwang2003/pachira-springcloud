package com.pachiraframework.party.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pachiraframework.party.Services;
import com.pachiraframework.party.config.FeignSkipBadRequestsConfiguration;

/**
 * @author KevinWang
 *
 */
@FeignClient(name = Services.OAUTH2,configuration=FeignSkipBadRequestsConfiguration.class)
public interface Oauth2Client {
	@PostMapping("/oauth/token")
	ResponseEntity<String> login(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("grant_type") String grantType,@RequestParam("scope") String scope,@RequestParam("client_id")String clientId,@RequestParam("client_secret")String clientSecret);
}

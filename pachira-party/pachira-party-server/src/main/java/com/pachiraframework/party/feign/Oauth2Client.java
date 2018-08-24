package com.pachiraframework.party.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pachiraframework.party.Services;

/**
 * @author KevinWang
 *
 */
@FeignClient(name = Services.OAUTH2)
public interface Oauth2Client {
	@PostMapping("/oauth/token")
	String login(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("grant_type") String grantType,@RequestParam("scope") String scope,@RequestParam("client_id")String clientId,@RequestParam("client_secret")String clientSecret);
}

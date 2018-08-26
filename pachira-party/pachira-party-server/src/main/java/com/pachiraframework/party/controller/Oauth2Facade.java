package com.pachiraframework.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pachiraframework.party.feign.Oauth2Client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Component
public class Oauth2Facade {
	private String clientId = "app1";
	private String clientSecret = "123456";
	@Autowired
	private Oauth2Client oauth2Client;
	/**
	 * 以ouauth2的password模式登录
	 * @param loginId
	 * @param password
	 * @param scope
	 * @return
	 */
	public String passwordLogin(String loginId,String password,String scope) {
		ResponseEntity<String> result = oauth2Client.login(loginId, password, "password", scope, clientId, clientSecret);
		log.info("{}",result);
		return result.getBody();
	}
}

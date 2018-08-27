package com.pachiraframework.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.config.FeignSkipBadRequestsConfiguration.FeignBadResponseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 登录操作
 * 
 * @author KevinWang
 *
 */
@Api("pachira-party模块用户登录API")
@RestController
@RequestMapping("/v1/party/")
public class LoginController extends AbstractPartyController {
	private Gson gson = new GsonBuilder().create();
	@Autowired
	private Oauth2Facade oauth2Facade;

	@ApiOperation(value = "用户登录", notes = "用户登录", consumes = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@ApiImplicitParams({ @ApiImplicitParam(name = "login_id", value = "登录帐号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String") })
	public ResponseEntity<ExecuteResult<String>> login(@RequestParam("login_id") String loginId,
			@RequestParam String password) {
		String scope = "app";
		try {
			String token = oauth2Facade.passwordLogin(loginId, password, scope);
			return new ResponseEntity<ExecuteResult<String>>(ExecuteResult.newSuccessResult(token),HttpStatus.OK);
		}catch(FeignBadResponseException e) {
			String error = errorMessage(e.getBody());
			return new ResponseEntity<ExecuteResult<String>>(ExecuteResult.newErrorResult(error),HttpStatus.valueOf(e.getStatus()));
		}
	}
	
	private String errorMessage(String bodyJson) {
		BadResponse bad = gson.fromJson(bodyJson, BadResponse.class);
		return bad.getMessage();
	}
	
	@Data
	private static class BadResponse{
		@SerializedName("error_description")
		private String message;
		@SerializedName("error")
		private String code;
	}
}

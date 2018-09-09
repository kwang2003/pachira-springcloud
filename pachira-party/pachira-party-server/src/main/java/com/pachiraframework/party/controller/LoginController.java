package com.pachiraframework.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.config.FeignSkipBadRequestsConfiguration.FeignBadResponseException;
import com.pachiraframework.party.dto.LoginRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	public ResponseEntity<ExecuteResult<String>> login(@ApiParam(name="用户登录信息",value="传入json格式",required=true) @RequestBody LoginRequestDto loginRequest) {
		String scope = "app";
		try {
			String token = oauth2Facade.passwordLogin(loginRequest.getLoginId(), loginRequest.getPassword(), scope);
			return new ResponseEntity<ExecuteResult<String>>(ExecuteResult.newSuccessResult(token),HttpStatus.OK);
		}catch(FeignBadResponseException e) {
			String error = errorMessage(e.getBody());
			return new ResponseEntity<ExecuteResult<String>>(ExecuteResult.newErrorResult(error),HttpStatus.OK);
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

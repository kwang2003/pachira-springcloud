package com.pachiraframework.party.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dto.CreateUserLoginHistoryDto;
import com.pachiraframework.party.entity.UserLogin;
import com.pachiraframework.party.entity.UserLoginHistory;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/v1/party/")
public interface UserApi {
	@ApiOperation(value = "根据用户ID获取用户信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<ExecuteResult<UserLogin>> getUser(@PathVariable("userId") Long userId);
	
	@ApiOperation(value = "根据用户登录帐号获取用户信息", notes = "根据登录帐号来获取用户详细信息")
	@ApiImplicitParam(name = "login_id", value = "用户登录帐号", required = true, dataType = "String")
	@RequestMapping(value = "/users/", method = RequestMethod.GET)
	public ResponseEntity<ExecuteResult<UserLogin>> getUser(@RequestParam(name="login_id") String loginId);
	
	@ApiOperation(value = "保存用户登录历史记录", notes = "根据登录帐号来获取用户详细信息")
	@ApiImplicitParam(name = "login_id", value = "用户登录帐号", required = true, dataType = "String")
	@RequestMapping(value = "/users/login_history", method = RequestMethod.POST)
	public ResponseEntity<ExecuteResult<UserLoginHistory>> loginHistory(CreateUserLoginHistoryDto loginDto);
}

package com.pachiraframework.party.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.api.UserApi;
import com.pachiraframework.party.dto.CreateUserLoginHistoryDto;
import com.pachiraframework.party.entity.UserLogin;
import com.pachiraframework.party.entity.UserLoginHistory;
import com.pachiraframework.party.service.UserLoginService;

/**
 * 用户相关方法
 * 
 * @author KevinWang
 *
 */
@RestController
public class UserController extends AbstractPartyController implements UserApi{
	@Autowired
	private UserLoginService userService;
	@Override
	public ResponseEntity<ExecuteResult<UserLogin>> getUser(Long userId) {
		return Optional.ofNullable(userService.get(userId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<ExecuteResult<UserLogin>> getUser(String loginId) {
		return Optional.ofNullable(userService.get(loginId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<ExecuteResult<UserLoginHistory>> loginHistory(@RequestBody CreateUserLoginHistoryDto loginDto) {
		return Optional.ofNullable(userService.createLoginHistory(loginDto)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

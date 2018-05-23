package com.pachiraframework.scheduler.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.scheduler.util.JWTUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

/**
 * @author wangxuzheng
 *
 */
@RestController
@RequestMapping("/user/")
public class LoginController {
	@RequestMapping(path = "/login", method = { RequestMethod.POST })
	public ResponseEntity<ExecuteResult<UserEntry>> search(@RequestBody LoginBean login) {
		String name = login.getName();
		String password = login.getPassword();
		if("admin".equals(name) && "111111".equals(password)) {
			UserEntry user = new UserEntry();
			user.setId(1L);
			String token = generateToken(user.getId());
			user.setToken(token);
			ExecuteResult<UserEntry> result = ExecuteResult.newSuccessResult(user);
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.ok(ExecuteResult.newErrorResult("用户名或密码错误"));
	}
	
	@Data
	public static class UserEntry{
		private Long id;
		private String token;
	}
	
	@Data
	public static class LoginBean{
		private String name;
		private String password;
	}
	
	private String generateToken(Long userId) {
		//5分钟token过期
		Date exp = new Date(System.currentTimeMillis()+50*60*1000);
	    String jwt = Jwts.builder()
	            .signWith(SignatureAlgorithm.HS256,JWTUtil.SECRET_KEY)
	            .setExpiration(exp)
	            .setIssuer("scheduler")
	            .claim("user_id",userId)
	            .claim("name","admin")
	            .compact();
	    return jwt;
	}
}

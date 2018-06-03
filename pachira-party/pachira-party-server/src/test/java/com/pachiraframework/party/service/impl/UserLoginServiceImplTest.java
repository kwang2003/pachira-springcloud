package com.pachiraframework.party.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.dto.CreateSimplePersonLoginUserDto;
import com.pachiraframework.party.entity.UserLogin;
import com.pachiraframework.party.service.UserLoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端服务测试
 * 
 * @author KevinWang
 *
 */
@Slf4j
public class UserLoginServiceImplTest extends AbstractPartyTestCase {
	@Autowired
	private UserLoginService userLoginService;
	
	@Test
	public void testGetString() {
		String loginId = "admin";
		ExecuteResult<UserLogin> result = userLoginService.get(loginId);
		assertThat(result.isSuccess(), is(true));
		UserLogin user = result.getResult();
		assertThat(user, notNullValue());
		assertThat(user.getPartyName(), equalTo("一号首长"));
		log.info("{}",user);
	}
	
	@Test
	public void testCreate() {
		CreateSimplePersonLoginUserDto dto = new CreateSimplePersonLoginUserDto();
		dto.setLoginId("abc");
		dto.setCurrentUser("大王");
		dto.setPassword("123456");
		dto.setName("一號首長");
		ExecuteResult<UserLogin> result = userLoginService.create(dto);
		assertThat(result.isSuccess(), is(true));
		log.info("{}",result.getResult());
	}
}
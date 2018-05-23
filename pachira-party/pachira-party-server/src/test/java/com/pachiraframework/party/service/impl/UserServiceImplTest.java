package com.pachiraframework.party.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.entity.User;
import com.pachiraframework.party.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端服务测试
 * 
 * @author KevinWang
 *
 */
@Slf4j
public class UserServiceImplTest extends AbstractPartyTestCase {
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetString() {
		String loginId = "admin";
		ExecuteResult<User> result = userService.get(loginId);
		assertThat(result.isSuccess(), is(true));
		User user = result.getResult();
		assertThat(user, notNullValue());
		assertThat(user.getPartyName(), equalTo("一号首长"));
		log.info("{}",user);
	}
}
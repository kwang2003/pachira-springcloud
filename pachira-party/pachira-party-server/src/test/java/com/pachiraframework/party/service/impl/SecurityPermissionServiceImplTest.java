package com.pachiraframework.party.service.impl;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.entity.SecurityPermission;
import com.pachiraframework.party.service.SecurityPermissionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
public class SecurityPermissionServiceImplTest extends AbstractPartyTestCase{
	@Autowired
	private SecurityPermissionService securityPermissionService;
	
	@Test
	public void testGetById() {
		String id = "ACCOUNTING_ADMIN";
		ExecuteResult<SecurityPermission> result = securityPermissionService.get(id);
		assertThat(result.isSuccess(), is(true));
		SecurityPermission permission = result.getResult();
		assertThat(permission, notNullValue());
		assertThat(permission.getId(), equalTo(id));
		assertThat(permission.getDescription(), notNullValue());
		log.info("{}",permission);
	}
	
}

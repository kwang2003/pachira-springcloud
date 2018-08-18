package com.pachiraframework.party.service.impl;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.entity.SecurityGroup;
import com.pachiraframework.party.service.SecurityGroupService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
public class SecurityGroupServiceImplTest extends AbstractPartyTestCase{
	@Autowired
	private SecurityGroupService securityGroupService;
	
	@Test
	public void testGetById() {
		String id = "ACCTG_FUNCTNL_ADMIN";
		ExecuteResult<SecurityGroup> result = securityGroupService.get(id);
		assertThat(result.isSuccess(), is(true));
		SecurityGroup group = result.getResult();
		assertThat(group, notNullValue());
		assertThat(group.getId(), equalTo(id));
		assertThat(group.getDescription(), notNullValue());
		log.info("{}",group);
	}
	
}

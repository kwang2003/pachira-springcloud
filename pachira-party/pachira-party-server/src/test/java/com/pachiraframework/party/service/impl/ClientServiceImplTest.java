package com.pachiraframework.party.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.entity.Client;
import com.pachiraframework.party.service.ClientService;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端服务测试
 * 
 * @author KevinWang
 *
 */
@Slf4j
public class ClientServiceImplTest extends AbstractPartyTestCase {
	@Autowired
	private ClientService clientService;

	@Test
	public void testGetLong() {
		Long id = 1L;
		ExecuteResult<Client> result = clientService.get(id);
		assertThat(result.isSuccess(),is(true));
		Client client = result.getResult();
		assertThat(client, notNullValue());
		assertThat(client.getId(), equalTo(1L));
		log.info("{}",client);
	}
}
package com.pachiraframework.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.ClientDao;
import com.pachiraframework.party.entity.Client;
import com.pachiraframework.party.service.ClientService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;

	@Override
	public ExecuteResult<Client> get(Long id) {
		log.info("id={}",id);
		Client client = clientDao.getById(id);
		return ExecuteResult.newSuccessResult(client);
	}

	@Override
	public ExecuteResult<Client> get(String clientId) {
		log.info("clientId={}",clientId);
		Client client = clientDao.getByClientId(clientId);
		return ExecuteResult.newSuccessResult(client);
	}
}

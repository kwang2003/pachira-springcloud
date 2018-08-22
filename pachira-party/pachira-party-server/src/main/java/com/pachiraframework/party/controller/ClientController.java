package com.pachiraframework.party.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.api.ClientApi;
import com.pachiraframework.party.entity.Client;
import com.pachiraframework.party.service.ClientService;

/**
 * 接入客户端应用相关的controller方法
 * @author KevinWang
 *
 */
@RestController
public class ClientController extends AbstractPartyController implements ClientApi{
	@Autowired
	private ClientService clientService;
	
	@Override
	public ResponseEntity<ExecuteResult<Client>> getClient(Long clientId) {
		return Optional.ofNullable(clientService.get(clientId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@Override
	public ResponseEntity<ExecuteResult<Client>> getClient(String clientId) {
		return Optional.ofNullable(clientService.get(clientId)).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

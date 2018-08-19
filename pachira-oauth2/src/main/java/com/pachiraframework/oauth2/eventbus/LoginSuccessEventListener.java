package com.pachiraframework.oauth2.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.oauth2.Apis;
import com.pachiraframework.party.dto.CreateUserLoginHistoryDto;
import com.pachiraframework.party.entity.Client;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Component
public class LoginSuccessEventListener extends AbstractEventListener {
	@Autowired
	private RestTemplate restTemplate;
	@Subscribe
	@AllowConcurrentEvents
	public void saveLoginHistory(LoginSuccessEvent event) {
		log.info("用户[{}]在[{}]登录成功.", event.getLoginId(), event.getIp());
		CreateUserLoginHistoryDto dto = new CreateUserLoginHistoryDto();
		dto.setLoginId(event.getLoginId());
		dto.setLoginIp(event.getIp());
		ParameterizedTypeReference<ExecuteResult<Client>> typeRef = new ParameterizedTypeReference<ExecuteResult<Client>>() {  }; 
		HttpEntity<CreateUserLoginHistoryDto> requestEntity = new HttpEntity<CreateUserLoginHistoryDto>(dto);
		ResponseEntity<ExecuteResult<Client>> responseEntity = restTemplate.exchange(Apis.PARTY_USERS_LOGIN_HISTORY, HttpMethod.POST, requestEntity, typeRef);
		log.info("{}",responseEntity);
	}
}

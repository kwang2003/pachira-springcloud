package com.pachiraframework.oauth2.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.oauth2.feign.ClientClient;
import com.pachiraframework.party.entity.Client;

import lombok.extern.slf4j.Slf4j;
/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class DefaultClientDetailsService implements ClientDetailsService {
	@Autowired
	private ClientClient clientClient;
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ResponseEntity<ExecuteResult<Client>> responseEntity = clientClient.getClient(clientId); 
		ExecuteResult<Client> result = responseEntity.getBody(); 
		if(!result.isSuccess()) {
			log.info("client:{} 加载失败，错误原因：{}",clientId,result.getMessage());
			throw new ClientRegistrationException("客户端查询错误:"+result.getMessage());
		}
		if(Objects.isNull(result.getResult())) {
			log.info("找不到clientID={}",clientId);
			throw new NoSuchClientException("应用:"+clientId+"不存在");
		}
		Client client = result.getResult();
		BaseClientDetails details = new BaseClientDetails();
		details.setClientId(client.getClientId());
		details.setClientSecret(client.getClientSecret());//加密后的字符串
		Set<String> scopes = new HashSet<>();
		Splitter.on(',').split(Strings.nullToEmpty(client.getScope())).forEach(str->scopes.add(str));
		details.setScope(scopes);
		Set<String> authorizedGrantTypes = new HashSet<>();
		Splitter.on(',').split(Strings.nullToEmpty(client.getAuthorizedGrantTypes())).forEach(str->authorizedGrantTypes.add(str));
		details.setAuthorizedGrantTypes(authorizedGrantTypes);;
		//直接写死的情况
//		BaseClientDetails details = new BaseClientDetails();
//		details.setClientId("client");
//		details.setClientSecret(new BCryptPasswordEncoder().encode("secret"));
//		Set<String> scopes = new HashSet<>();
//		scopes.add("app");
//		details.setScope(scopes);
//		Set<String> authorizedGrantTypes = new HashSet<>();
//		authorizedGrantTypes.add("password");
//		authorizedGrantTypes.add("authorization_code");
//		authorizedGrantTypes.add("client_credentials");
//		authorizedGrantTypes.add("refresh_token");
//		details.setAuthorizedGrantTypes(authorizedGrantTypes);;
		return details;
	}

}

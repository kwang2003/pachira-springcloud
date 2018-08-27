package com.pachiraframework.oauth2.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.oauth2.feign.UserClient;
import com.pachiraframework.party.dto.CreateUserLoginHistoryDto;
import com.pachiraframework.party.entity.UserLoginHistory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Component
public class LoginFailedEventListener extends AbstractEventListener
		implements ApplicationListener<AbstractAuthenticationFailureEvent> {
	@Autowired
	private UserClient userClient;

	@Subscribe
	@AllowConcurrentEvents
	public void saveLoginHistory(LoginFailedEvent event) {
		log.info("用户[{}]在[{}]登录失败.", event.getLoginId(), event.getIp());
		CreateUserLoginHistoryDto dto = new CreateUserLoginHistoryDto();
		dto.setLoginId(event.getLoginId());
		dto.setLoginIp(event.getIp());
		dto.setMessage(event.getMessage());
		dto.setSuccess(false);
		ResponseEntity<ExecuteResult<UserLoginHistory>> responseEntity = userClient.loginHistory(dto);
		log.info("{}", responseEntity);
	}

	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		log.info("{}",event);
		Authentication authentication = event.getAuthentication();
		Object principal = authentication.getPrincipal();
		if(principal instanceof String) {
			LoginFailedEvent e = new LoginFailedEvent();
			e.setIp("");
			e.setLoginId(principal.toString());
			e.setMessage(event.getException().getMessage());
			saveLoginHistory(e);
		}else {
			log.warn("");
		}
	}
}

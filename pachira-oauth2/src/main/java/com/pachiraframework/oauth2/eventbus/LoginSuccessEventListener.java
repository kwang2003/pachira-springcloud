package com.pachiraframework.oauth2.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
public class LoginSuccessEventListener extends AbstractEventListener implements ApplicationListener<AuthenticationSuccessEvent>{
	@Autowired
	private UserClient userClient;
	@Subscribe
	@AllowConcurrentEvents
	public void saveLoginHistory(LoginSuccessEvent event) {
		log.info("用户[{}]在[{}]登录成功.", event.getLoginId(), event.getIp());
		CreateUserLoginHistoryDto dto = new CreateUserLoginHistoryDto();
		dto.setLoginId(event.getLoginId());
		dto.setLoginIp(event.getIp());
		ResponseEntity<ExecuteResult<UserLoginHistory>> responseEntity =userClient.loginHistory(dto);
		log.info("{}",responseEntity);
	}
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		log.info("{}",event);
		Authentication authentication = event.getAuthentication();
		Object principal = authentication.getPrincipal();
		if(principal instanceof User) {
			User user = (User)principal;
			LoginSuccessEvent e = new LoginSuccessEvent();
			e.setIp("");
			e.setLoginId(user.getUsername());
			saveLoginHistory(e);
		}else {
			log.warn("");
		}
	}
}

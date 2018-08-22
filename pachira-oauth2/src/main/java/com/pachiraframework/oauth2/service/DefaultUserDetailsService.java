package com.pachiraframework.oauth2.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.oauth2.feign.UserClient;
import com.pachiraframework.party.entity.UserLogin;
import com.pachiraframework.party.entity.UserLogin.EnabledEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class DefaultUserDetailsService implements UserDetailsService {
	@Autowired
	private UserClient userClient;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		ResponseEntity<ExecuteResult<UserLogin>> responseEntity = userClient.getUser(name);
		ExecuteResult<UserLogin> result = responseEntity.getBody(); 
		if(!result.isSuccess()) {
			log.info("user name:{} 加载失败，错误原因：{}",name,result.getMessage());
			throw new BadCredentialsException("用户查询错误:"+result.getMessage());
		}
		if(Objects.isNull(result.getResult())) {
			log.info("找不到登录帐号={}",name);
			throw new UsernameNotFoundException("登录名:"+name+"不存在");
		}
		UserLogin userLogin = result.getResult();
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("admin"));//改成从数据库中读取帐号对应的权限
		User userDetail = new User(name,userLogin.getPassword(),EnabledEnum.Y.toString().equals(userLogin.getEnabled()),true, true, true,authorities);
		return userDetail;
	}
}

package com.pachiraframework.oauth2.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class DefaultUserDetailsService implements UserDetailsService {
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		ParameterizedTypeReference<ExecuteResult<User>> typeRef = new ParameterizedTypeReference<ExecuteResult<User>>() {  }; 
		ResponseEntity<ExecuteResult<User>> responseEntity = restTemplate.exchange("http://localhost:8080/v1/party/users/?login_id="+name, HttpMethod.GET, new HttpEntity<>(null), typeRef); 
		ExecuteResult<User> result = responseEntity.getBody(); 
		if(!result.isSuccess()) {
			log.info("user name:{} 加载失败，错误原因：{}",name,result.getMessage());
			throw new BadCredentialsException("用户查询错误:"+result.getMessage());
		}
		if(Objects.isNull(result.getResult())) {
			log.info("找不到登录帐号={}",name);
			throw new UsernameNotFoundException("登录名:"+name+"不存在");
		}
		User user = result.getResult();
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("admin"));//改成从数据库中读取帐号对应的权限
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(name,user.getPassword(),authorities);
		return userDetail;
	}
}

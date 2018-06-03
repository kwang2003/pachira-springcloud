package com.pachiraframework.party.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dto.CreateSimplePersonLoginUserDto;
import com.pachiraframework.party.entity.UserLogin;

/**
 * 用户登录相关的服务
 * @author KevinWang
 *
 */
public interface UserLoginService {
	/**
	 * 根据用户ID获取用户明细
	 * @param id
	 * @return
	 */
	ExecuteResult<UserLogin> get(Long id); 
	
	/**
	 * 通过登录ID获取用户信息
	 * @param loginId
	 * @return
	 */
	ExecuteResult<UserLogin> get(String loginId);
	
	/**
	 * 给已有人员创建一个新的帐号
	 * @param createSimplePersonLoginUserDto
	 * @return
	 */
	ExecuteResult<UserLogin> create(CreateSimplePersonLoginUserDto createSimplePersonLoginUserDto);
}

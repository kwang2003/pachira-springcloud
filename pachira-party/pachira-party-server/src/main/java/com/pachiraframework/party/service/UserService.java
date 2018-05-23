package com.pachiraframework.party.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.User;

/**
 * 用户登录相关的服务
 * @author KevinWang
 *
 */
public interface UserService {
	/**
	 * 根据用户ID获取用户明细
	 * @param id
	 * @return
	 */
	ExecuteResult<User> get(Long id); 
	
	/**
	 * 通过登录ID获取用户信息
	 * @param loginId
	 * @return
	 */
	ExecuteResult<User> get(String loginId);
}

package com.pachiraframework.party.service;

import java.security.SecurityPermission;

import com.pachiraframework.common.ExecuteResult;

/**
 * 用户权限服务
 * @author KevinWang
 *
 */
public interface UserSecurityPermissionService {
	/**
	 * 获取用户拥有的授权列表
	 * @param userId
	 * @return
	 */
	public ExecuteResult<SecurityPermission> listUserPermissions(String userId);
}

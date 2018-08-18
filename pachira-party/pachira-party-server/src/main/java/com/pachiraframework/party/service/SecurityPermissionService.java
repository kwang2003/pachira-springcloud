package com.pachiraframework.party.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.SecurityPermission;

/**
 * 权限
 * 
 * @author KevinWang
 *
 */
public interface SecurityPermissionService {
	/**
	 * 通过ID获取security_permission
	 * @param id
	 * @return
	 */
	ExecuteResult<SecurityPermission> get(String id);
}

package com.pachiraframework.party.service.impl;

import java.security.SecurityPermission;

import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.service.UserSecurityPermissionService;

/**
 * @author KevinWang
 *
 */
@Service
public class UserSecurityPermissionServiceImpl implements UserSecurityPermissionService {

	@Override
	public ExecuteResult<SecurityPermission> listUserPermissions(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

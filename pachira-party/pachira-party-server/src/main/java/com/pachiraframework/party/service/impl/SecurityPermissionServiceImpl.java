package com.pachiraframework.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.SecurityPermissionDao;
import com.pachiraframework.party.entity.SecurityPermission;
import com.pachiraframework.party.service.SecurityPermissionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class SecurityPermissionServiceImpl implements SecurityPermissionService {
	@Autowired
	private SecurityPermissionDao securityPermissionDao;
	@Override
	public ExecuteResult<SecurityPermission> get(String id) {
		log.info("id={}",id);
		SecurityPermission permission = securityPermissionDao.getById(id);
		return ExecuteResult.newSuccessResult(permission);
	}

}

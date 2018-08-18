package com.pachiraframework.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.SecurityGroupDao;
import com.pachiraframework.party.entity.SecurityGroup;
import com.pachiraframework.party.service.SecurityGroupService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class SecurityGroupServiceImpl implements SecurityGroupService {
	@Autowired
	private SecurityGroupDao securityGroupDao;
	@Override
	public ExecuteResult<SecurityGroup> get(String id) {
		log.info("id={}",id);
		SecurityGroup group = securityGroupDao.getById(id);
		return ExecuteResult.newSuccessResult(group);
	}

}

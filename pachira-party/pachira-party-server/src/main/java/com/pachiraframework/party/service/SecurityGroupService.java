package com.pachiraframework.party.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.SecurityGroup;

/**
 * 安全组
 * 
 * @author KevinWang
 *
 */
public interface SecurityGroupService {
	/**
	 * 通过ID获取security_group
	 * @param id
	 * @return
	 */
	ExecuteResult<SecurityGroup> get(String id);
}

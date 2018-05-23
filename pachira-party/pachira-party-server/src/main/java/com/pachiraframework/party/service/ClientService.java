package com.pachiraframework.party.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.Client;

/**
 * 接入的第三方应用相关的服务
 * @author KevinWang
 *
 */
public interface ClientService {
	/**
	 * 通过ID获取client客户端信息
	 * @param id
	 * @return
	 */
	ExecuteResult<Client> get(Long id);
	
	/**
	 * 通过客户端编号获取client客户端信息
	 * @param clientId
	 * @return
	 */
	ExecuteResult<Client> get(String clientId);
}

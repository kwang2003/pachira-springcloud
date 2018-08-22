package com.pachiraframework.party.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.entity.Client;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangxuzheng
 *
 */
@RequestMapping("/v1/party/")
public interface ClientApi {
	@RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ID获取客户端信息", notes = "根据url的id来获取客户端详细信息")
	@ApiImplicitParam(name = "clientId", value = "客户端ID", required = true, dataType = "Long")
	public ResponseEntity<ExecuteResult<Client>> getClient(@PathVariable("clientId") Long clientId);
	
	@RequestMapping(value = "/clients/", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ClientId获取客户端信息", notes = "根据url的clientId来获取客户端详细信息")
	@ApiImplicitParam(name = "client_id", value = "客户端编号ID", required = true, dataType = "String")
	public ResponseEntity<ExecuteResult<Client>> getClient(@RequestParam(name="client_id") String clientId);
}

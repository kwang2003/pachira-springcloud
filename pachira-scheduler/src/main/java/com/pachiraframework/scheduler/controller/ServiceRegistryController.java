package com.pachiraframework.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.scheduler.entity.ServiceRegistry;
import com.pachiraframework.scheduler.service.ServiceRegistryService;
import com.pachiraframework.web.controller.BaseController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author kevin
 *
 */
@RestController
@RequestMapping("/job/")
public class ServiceRegistryController extends BaseController {
	@Autowired
	private ServiceRegistryService serviceRegistryService;
	
	@ApiOperation(value = "查询所有的注册中心", notes = "系统中所有的注册中心", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "注册中西列表") })
	@RequestMapping(path = "/registry/all", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<List<ServiceRegistry>>> all() {
		ExecuteResult<List<ServiceRegistry>> result = serviceRegistryService.getAll();
		return ResponseEntity.ok(result);
	}
}

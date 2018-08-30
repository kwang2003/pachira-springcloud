package com.pachiraframework.basic.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pachiraframework.basic.entity.Region;
import com.pachiraframework.common.ExecuteResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kevin Wang
 *
 */
@RequestMapping("/v1/basic")
public interface RegionApi {
	@RequestMapping(value = "/regions/{regionId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户ID获取区域信息", notes = "根据url的id来获取区域详细信息")
	@ApiImplicitParam(name = "regionId", value = "区域ID", required = true, dataType = "Long",example="1")
	public ResponseEntity<ExecuteResult<Region>> getRegion(@PathVariable("regionId") Long regionId);
}

package com.pachiraframework.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.basic.api.RegionApi;
import com.pachiraframework.basic.entity.Region;
import com.pachiraframework.common.ExecuteResult;

/**
 * @author KevinWang
 *
 */
@RestController
public class RegionController extends AbstractBasicController implements RegionApi {
	@Override
	public ResponseEntity<ExecuteResult<Region>> getRegion(Long regionId) {
		return null;
	}

}

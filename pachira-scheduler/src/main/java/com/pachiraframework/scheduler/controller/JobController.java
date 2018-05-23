package com.pachiraframework.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.domain.Page;
import com.pachiraframework.scheduler.dto.AddJob;
import com.pachiraframework.scheduler.dto.SearchJobCriteria;
import com.pachiraframework.scheduler.dto.SearchJobHistoryCriteria;
import com.pachiraframework.scheduler.entity.Job;
import com.pachiraframework.scheduler.entity.JobHistory;
import com.pachiraframework.scheduler.service.JobHistoryService;
import com.pachiraframework.scheduler.service.JobService;
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
public class JobController extends BaseController {
	@Autowired
	private JobService jobService;
	@Autowired
	private JobHistoryService jobHistoryService;

	@ApiOperation(value = "任务查询", notes = "根据条件，检索匹配的任务列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "分页的任务列表信息") })
	@RequestMapping(path = "/search", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<Page<Job>>> search(SearchJobCriteria criteria) {
		Page<Job> page = jobService.search(criteria);
		ExecuteResult<Page<Job>> result = ExecuteResult.newSuccessResult(page);
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "添加新任务", notes = "创建新的任务信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "创建成功后的任务信息") })
	@RequestMapping(path = "/add", method = { RequestMethod.POST })
	public ResponseEntity<ExecuteResult<Job>> add(@RequestBody AddJob job) {
		ExecuteResult<Job> result = jobService.add(job);
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "任务执行历史查询", notes = "根据条件，检索匹配的任务执行历史列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "分页的任务执行历史记录列表信息") })
	@RequestMapping(path = "/history", method = { RequestMethod.GET })
	public ResponseEntity<ExecuteResult<Page<JobHistory>>> search(SearchJobHistoryCriteria criteria) {
		Page<JobHistory> page = jobHistoryService.search(criteria);
		ExecuteResult<Page<JobHistory>> result = ExecuteResult.newSuccessResult(page);
		return ResponseEntity.ok(result);
	}
}

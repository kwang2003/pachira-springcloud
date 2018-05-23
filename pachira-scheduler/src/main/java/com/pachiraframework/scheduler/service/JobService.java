package com.pachiraframework.scheduler.service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.domain.Page;
import com.pachiraframework.scheduler.dto.AddJob;
import com.pachiraframework.scheduler.dto.SearchJobCriteria;
import com.pachiraframework.scheduler.entity.Job;

/**
 * @author kevin
 *
 */
public interface JobService {
	/**
	 * 任务信息查询
	 * @param criteria
	 * @return
	 */
	public Page<Job> search(SearchJobCriteria criteria);
	/**
	 * 添加一个新的job
	 * @param job
	 * @return
	 */
	public ExecuteResult<Job> add(AddJob job);
	/**
	 * 删除一个任务
	 * @param jobId
	 * @return
	 */
	public ExecuteResult<Job> delete(Long jobId);
}

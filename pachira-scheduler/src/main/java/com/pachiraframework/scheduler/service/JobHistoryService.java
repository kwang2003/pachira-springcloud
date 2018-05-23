package com.pachiraframework.scheduler.service;

import com.pachiraframework.domain.Page;
import com.pachiraframework.scheduler.dto.SearchJobHistoryCriteria;
import com.pachiraframework.scheduler.entity.JobHistory;

/**
 * @author wangxuzheng
 *
 */
public interface JobHistoryService {
	/**
	 * 任务信息查询
	 * @param criteria
	 * @return
	 */
	public Page<JobHistory> search(SearchJobHistoryCriteria criteria);
}

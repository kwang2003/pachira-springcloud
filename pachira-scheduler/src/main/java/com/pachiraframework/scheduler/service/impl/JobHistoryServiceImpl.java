package com.pachiraframework.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.domain.Order;
import com.pachiraframework.domain.Order.Direction;
import com.pachiraframework.domain.Page;
import com.pachiraframework.domain.WrappedPageRequest;
import com.pachiraframework.scheduler.dao.JobHistoryDao;
import com.pachiraframework.scheduler.dto.SearchJobHistoryCriteria;
import com.pachiraframework.scheduler.entity.JobHistory;
import com.pachiraframework.scheduler.service.JobHistoryService;

/**
 * @author wangxuzheng
 *
 */
@Service
public class JobHistoryServiceImpl implements JobHistoryService {
	@Autowired
	private JobHistoryDao jobHistoryDao;
	@Override
	public Page<JobHistory> search(SearchJobHistoryCriteria criteria) {
		criteria.getOrders().add(new Order("started_at", Direction.DESC));
		WrappedPageRequest pageRequest = new WrappedPageRequest(criteria);
		return jobHistoryDao.findByPage(pageRequest);
	}

}

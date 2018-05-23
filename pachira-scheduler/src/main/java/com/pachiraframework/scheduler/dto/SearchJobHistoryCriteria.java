package com.pachiraframework.scheduler.dto;

import com.pachiraframework.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * job查询条件封装
 * @author kevin
 *
 */
@Getter
@Setter
public class SearchJobHistoryCriteria extends PageRequest{
	private static final long serialVersionUID = 1522179078594164278L;
	private String startDate;
	private String endDate;
	private Long jobId;
}

package com.pachiraframework.scheduler.service.impl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.domain.Page;
import com.pachiraframework.scheduler.AbstractSchedulerApplicationTest;
import com.pachiraframework.scheduler.dto.AddJob;
import com.pachiraframework.scheduler.dto.SearchJobCriteria;
import com.pachiraframework.scheduler.entity.Job;
import com.pachiraframework.scheduler.entity.Job.TypeEnum;
import com.pachiraframework.scheduler.service.JobService;

/**
 * @author kevin
 *
 */
public class JobServiceImplTest extends AbstractSchedulerApplicationTest{
	@Autowired
	private JobService jobService;
	
	@Test
	public void testAdd(){
		AddJob job = new AddJob();
		job.setCron("0/5 * * * * ?");
		job.setName("測試以下");
		job.setType(TypeEnum.HTTP.toString());
		job.setDescription("5秒钟执行一次");
		job.setMethod("GET");
		job.setInterfaceName("http://www.baidu.com");
		job.setTimeout(5000L);
		ExecuteResult<Job> result = jobService.add(job);
		Job newJob = result.getResult();
		assertThat(newJob.getId(), notNullValue());
		jobService.delete(newJob.getId());
		System.out.println("33333");
	}
	
	@Test
	public void testSearch() {
		SearchJobCriteria criteria = new SearchJobCriteria();
		Page<Job> page = jobService.search(criteria);
		assertThat(page.getTotalElements(), greaterThanOrEqualTo(1));
	}

}

package com.pachiraframework.scheduler.service.impl;

import org.springframework.stereotype.Service;

import com.pachiraframework.scheduler.service.DemoDubboService;

@Service
public class DemoDubboServiceImpl implements DemoDubboService {
	@Override
	public void doSomething() {
		System.out.println("**************************");
	}

}

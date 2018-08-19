package com.pachiraframework.oauth2.eventbus;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * @author KevinWang
 *
 */
public class Oauth2EventBus {
	private static final EventBus EVENT_BUS = init();
	private static EventBus init() {
		AsyncEventBus bus = new AsyncEventBus(Executors.newFixedThreadPool(10));
		return bus;
	}
	
	public static EventBus eventBus() {
		return EVENT_BUS;
	}
}

package com.pachiraframework.oauth2.eventbus;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author KevinWang
 *
 */
public abstract class AbstractEventListener implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		Oauth2EventBus.eventBus().register(this);
	}

}

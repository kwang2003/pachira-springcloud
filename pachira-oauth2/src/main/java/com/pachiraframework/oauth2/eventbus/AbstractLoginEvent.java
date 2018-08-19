package com.pachiraframework.oauth2.eventbus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录事件
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString
public abstract class AbstractLoginEvent {
	private String loginId;
	private String ip;
	/**
	 * 浏览器
	 */
	private String userAgent;
}

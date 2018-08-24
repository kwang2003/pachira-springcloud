package com.pachiraframework.oauth2.eventbus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录成功事件
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class LoginSuccessEvent extends AbstractLoginEvent{
	private String message;
}

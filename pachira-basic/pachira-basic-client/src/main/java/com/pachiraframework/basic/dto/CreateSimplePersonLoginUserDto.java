package com.pachiraframework.basic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 给已有person创建个人登录帐号--快速创建
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class CreateSimplePersonLoginUserDto extends CurrentUserAwareDto {
	private static final long serialVersionUID = -7969960169470360074L;
	private String loginId;
	private String password;
	private String name;
}

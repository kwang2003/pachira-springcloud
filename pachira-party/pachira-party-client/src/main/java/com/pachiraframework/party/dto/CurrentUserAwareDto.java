package com.pachiraframework.party.dto;

import com.pachiraframework.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 可以获取当前操作用户
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class CurrentUserAwareDto extends BaseDto {
	private static final long serialVersionUID = -667246168827942303L;
	/**
	 * 当前操作人员
	 */
	private String currentUser;
}

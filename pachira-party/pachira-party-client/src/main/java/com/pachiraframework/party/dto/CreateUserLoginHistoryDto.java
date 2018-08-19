package com.pachiraframework.party.dto;

import com.pachiraframework.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class CreateUserLoginHistoryDto extends BaseDto {
	private static final long serialVersionUID = 8428623148644617658L;
	private String loginId;
	private String loginIp;
}

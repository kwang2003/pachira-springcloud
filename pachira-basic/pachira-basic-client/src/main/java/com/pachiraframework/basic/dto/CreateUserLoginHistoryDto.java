package com.pachiraframework.basic.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString
public class CreateUserLoginHistoryDto implements Serializable{
	private static final long serialVersionUID = 8428623148644617658L;
	private String loginId;
	private String loginIp;
	private Boolean success;
	private String message;
}

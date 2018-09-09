package com.pachiraframework.party.dto;

import lombok.Data;

/**
 * 登陸
 * @author KevinWang
 *
 */
@Data
public class LoginRequestDto {
	private String loginId;
	private String password;
	private String type;
}

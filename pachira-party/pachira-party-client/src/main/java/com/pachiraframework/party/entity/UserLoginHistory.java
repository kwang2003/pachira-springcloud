package com.pachiraframework.party.entity;

import java.util.Date;

import com.pachiraframework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录历史
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UserLoginHistory extends BaseEntity<Long> {
	private static final long serialVersionUID = 7824231805102581946L;
	private Long userLoginId;
	private String loginId;
	private String loginIp;
	private Date loginDate;
	private Long partyId;
	private Integer status;
	private String message;

	@Getter
	@AllArgsConstructor
	public static enum StatusEnum{
		SUCCSSS(1),FAIL(0);
		private Integer status;
	}
}

package com.pachiraframework.party.entity;

import java.util.Date;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
public class UserLogin extends BaseEntity<Long> {
	private static final long serialVersionUID = -8277951089879857560L;
	private String loginId;
	private String password;
	private String enabled;
	private String lastLocal;
	private String lastTimeZone;
	/**
	 * 帐号禁用时间
	 */
	private Date disabledDate;
	private String disabledBy;
	/**
	 * ldap目录名称
	 */
	private String userLdapDn;
	private Long partyId;
	private String partyName;
	private String lastLoginIp;
	private Date lastLoginDate;
	
	public static enum EnabledEnum{
		Y,N;
	}
}

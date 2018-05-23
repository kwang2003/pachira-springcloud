package com.pachiraframework.party.entity;

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
public class User extends BaseEntity<Long> {
	private static final long serialVersionUID = -8277951089879857560L;
	// party 信息
	private Long partyId;
	private String partyName;
	private Integer partyType;
	
	private String loginId;
	private String password;
}

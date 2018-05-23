package com.pachiraframework.party.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 人员信息
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper=true)
public class Person extends Party {
	private static final long serialVersionUID = -8870493879190879467L;
	private String name;
	private String sex;
	
	public static enum SexEnum{
		MALE ,FEMALE;
	}
}

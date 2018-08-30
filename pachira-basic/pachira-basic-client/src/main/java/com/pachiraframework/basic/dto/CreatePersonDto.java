package com.pachiraframework.basic.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建Person DTO
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class CreatePersonDto extends CurrentUserAwareDto {
	private static final long serialVersionUID = 6468258491246221589L;
	private String name;
	private Date birthDate;
	private String statusId = "PARTY_ENABLED";
	private String gender;
	/**
	 * 体重
	 */
	private Double weight;
	/**
	 * 身高
	 */
	private Double height;
	/**
	 * 婚姻状态
	 */
	private String maritalStatus;
	/**
	 * 身份证号
	 */
	private String cardId;
	/**
	 * 护照号码
	 */
	private String passportNumber;
	/**
	 * 护照过期时间
	 */
	private Date passportExpireDate;
	/**
	 * 社保卡号
	 */
	private String socialSecurityNumber;
}

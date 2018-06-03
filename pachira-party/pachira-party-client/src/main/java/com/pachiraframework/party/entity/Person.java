package com.pachiraframework.party.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 人员信息
 * 
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
public class Person extends Party {
	private static final long serialVersionUID = -8870493879190879467L;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 出生日期
	 */
	private Date birthDate;
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

	public static enum GenderEnum {
		/**
		 * 男
		 */
		M,
		/**
		 * 女
		 */
		F,
		/**
		* 未知 
		*/
		N;
	}
	
	public static enum MaritalStatusEnum{
		/**
		 * 已婚 
		 */
		M,
		/**
		 * 未婚 
		 */
		U,
		/**
		 * 未知
		 */
		N
	}
}

package com.pachiraframework.party.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 组织
 * @author KevinWang
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class PartyGroup extends Party {
	private static final long serialVersionUID = 2263264080025198936L;
	/**
	 * 组织名称
	 */
	private String groupName;
	/**
	 * 官网地址
	 */
	private String officeSiteName;
	/**
	 * 备注
	 */
	private String comments;
	/**
	 * logo图片地址（完整）
	 */
	private String logoImageUrl;
}

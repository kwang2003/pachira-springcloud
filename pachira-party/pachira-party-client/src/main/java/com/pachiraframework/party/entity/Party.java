package com.pachiraframework.party.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团体对象，可以是个人，也可以是组织类型
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper=true)
public class Party extends BaseEntity<Long> {
	private static final long serialVersionUID = 4858790514600005120L;
	private String name;
	private Integer type;
	
	@Getter
	@AllArgsConstructor
	public static enum typeEnum{
		PERSON(1,"人员"),ORGANIZATION(2,"组织");
		private Integer type;
		private String name;
	}
}

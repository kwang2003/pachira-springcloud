package com.pachiraframework.party.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限组
 * 
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper = true)
public class SecurityGroup extends BaseEntity<String> {
	private static final long serialVersionUID = 7324142610000253080L;
	private String description;
}

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
public class SecurityPermission extends BaseEntity<String> {
	private static final long serialVersionUID = 4479386191410118136L;
	private String description;
}

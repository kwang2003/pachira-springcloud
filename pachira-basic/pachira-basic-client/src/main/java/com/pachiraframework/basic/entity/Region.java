package com.pachiraframework.basic.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 区域信息
 * @author wangxuzheng
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class Region extends BaseEntity<Long> {
	private static final long serialVersionUID = -5520289751945551327L;
	private String name;
	private Integer level;
	private Long parentId;
}

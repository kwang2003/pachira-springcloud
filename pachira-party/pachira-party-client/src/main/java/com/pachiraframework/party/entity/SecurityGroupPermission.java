package com.pachiraframework.party.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author KevinWang
 *
 */
@Data
public class SecurityGroupPermission implements Serializable {
	private static final long serialVersionUID = 5473542910465787042L;
	private String groupId;
	private String permissionId;
}

package com.pachiraframework.party.dao;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.party.entity.SecurityGroupPermission;

/**
 * @author KevinWang
 *
 */
@Repository
public class SecurityGroupPermissionDao extends AbstractPartyDao{
	public SecurityGroupPermission get(String groupId,String permissionId) {
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("group_id", "=", groupId));
		command.addWhereSqlCondition(SqlCondition.and("permission_id", "=", permissionId));
		return this.findOneBySqlCommand(command);
	}
}

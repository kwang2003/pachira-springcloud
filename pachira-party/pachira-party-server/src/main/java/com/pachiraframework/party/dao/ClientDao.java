package com.pachiraframework.party.dao;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.party.entity.Client;

/**
 * 对client表进行操作
 * @author KevinWang
 *
 */
@Repository
public class ClientDao extends AbstractPartyDao {
	public Client getByClientId(String clientId) {
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("client_id", "=", clientId));
		return this.findOneBySqlCommand(command);
	}
}

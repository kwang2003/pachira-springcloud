package com.pachiraframework.party.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.party.entity.Person;

/**
 * 人员表数据库操作
 * @author KevinWang
 *
 */
@Repository
public class PersonDao extends AbstractPartyDao {
	@Override
	public <T> T getById(Serializable id){
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("p.id", "=", id));
		return this.findOneBySqlCommand(command);
	}
	
	public Person getByCardId(String cardId) {
		SqlQueryCommand command = new SqlQueryCommand();
		command.addWhereSqlCondition(SqlCondition.and("p.card_id", "=", cardId));
		return this.findOneBySqlCommand(command);
	}
}

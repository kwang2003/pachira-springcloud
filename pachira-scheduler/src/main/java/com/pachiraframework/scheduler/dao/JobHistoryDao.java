package com.pachiraframework.scheduler.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.BaseDao;
import com.pachiraframework.dao.support.SqlColumnValue;
import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlUpdateCommand;
import com.pachiraframework.scheduler.entity.JobHistory.StatusEnum;

/**
 * @author kevin
 *
 */
@Repository
public class JobHistoryDao extends BaseDao {
	/**
	 * 标记任务执行成功
	 * @param id
	 * @return
	 */
	public int markSuccess(Long id,Date endedAt) {
		SqlUpdateCommand command = new SqlUpdateCommand();
		command.addWhereSqlCondition(SqlCondition.and("id", "=", id));
		command.addSqlColumnValue(new SqlColumnValue("status", StatusEnum.SUCCESS.toString()));
		command.addSqlColumnValue(new SqlColumnValue("ended_at", endedAt));
		return this.updateBySqlCommand(command);
	}
	
	/**
	 * 标记任务执行失败
	 * @param id
	 * @param message
	 * @return
	 */
	public int markFaild(Long id,Date endedAt,String message) {
		SqlUpdateCommand command = new SqlUpdateCommand();
		command.addWhereSqlCondition(SqlCondition.and("id", "=", id));
		command.addSqlColumnValue(new SqlColumnValue("status", StatusEnum.FAIL.toString()));
		command.addSqlColumnValue(new SqlColumnValue("message", message));
		command.addSqlColumnValue(new SqlColumnValue("ended_at", endedAt));
		return this.updateBySqlCommand(command);
	}
}

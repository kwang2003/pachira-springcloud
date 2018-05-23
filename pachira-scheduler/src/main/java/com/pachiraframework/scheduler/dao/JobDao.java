package com.pachiraframework.scheduler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pachiraframework.dao.BaseDao;
import com.pachiraframework.dao.support.SqlColumnValue;
import com.pachiraframework.dao.support.SqlCondition;
import com.pachiraframework.dao.support.SqlQueryCommand;
import com.pachiraframework.dao.support.SqlUpdateCommand;
import com.pachiraframework.entity.BaseEntity.IsDeletedEnum;
import com.pachiraframework.scheduler.entity.Job;

/**
 * @author kevin
 *
 */
@Repository
public class JobDao extends BaseDao {
	/**
	 * 查询未删除状态的任务列表
	 * @return
	 */
	public List<Job> getAll(){
		SqlQueryCommand command = new SqlQueryCommand();
		return this.findListBySqlCommand(command);
	}
	
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int delete(Long id) {
		SqlUpdateCommand command = new SqlUpdateCommand();
		command.addSqlColumnValue(new SqlColumnValue("is_deleted", IsDeletedEnum.DELETED.getIsDeleted()));
		command.addWhereSqlCondition(SqlCondition.and("id", "=", id));
		return this.updateBySqlCommand(command);
	}
}

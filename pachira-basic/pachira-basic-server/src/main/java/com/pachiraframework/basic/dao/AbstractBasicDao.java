package com.pachiraframework.basic.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pachiraframework.dao.BaseDao;

/**
 * 连接oauth2数据库操作的dao
 * 
 * @author KevinWang
 *
 */
public abstract class AbstractBasicDao extends BaseDao {
	@Autowired
	@Qualifier("partySqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

}

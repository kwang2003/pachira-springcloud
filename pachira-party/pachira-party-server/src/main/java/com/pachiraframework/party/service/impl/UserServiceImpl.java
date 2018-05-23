package com.pachiraframework.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.UserDao;
import com.pachiraframework.party.entity.User;
import com.pachiraframework.party.service.UserService;

/**
 * @author KevinWang
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public ExecuteResult<User> get(Long id) {
		User user = userDao.getById(id);
		return ExecuteResult.newSuccessResult(user);
	}
	@Override
	public ExecuteResult<User> get(String loginId) {
		User user = userDao.getByLoginId(loginId);
		return ExecuteResult.newSuccessResult(user);
	}

}

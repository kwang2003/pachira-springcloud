package com.pachiraframework.party.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.UserLoginDao;
import com.pachiraframework.party.dao.UserLoginHistoryDao;
import com.pachiraframework.party.dto.CreatePersonDto;
import com.pachiraframework.party.dto.CreateSimplePersonLoginUserDto;
import com.pachiraframework.party.dto.CreateUserLoginHistoryDto;
import com.pachiraframework.party.entity.Person;
import com.pachiraframework.party.entity.Person.GenderEnum;
import com.pachiraframework.party.entity.Person.MaritalStatusEnum;
import com.pachiraframework.party.entity.UserLogin;
import com.pachiraframework.party.entity.UserLogin.EnabledEnum;
import com.pachiraframework.party.entity.UserLoginHistory;
import com.pachiraframework.party.service.PersonService;
import com.pachiraframework.party.service.UserLoginService;
import com.pachiraframework.util.SnowflakeIdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	private PersonService personService;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private UserLoginHistoryDao userLoginHistoryDao;
	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;
	@Autowired
	@Qualifier("partyTransactionManager")
	private PlatformTransactionManager partyTransactionManager;
	@Override
	public ExecuteResult<UserLogin> get(Long id) {
		UserLogin user = userLoginDao.getById(id);
		return ExecuteResult.newSuccessResult(user);
	}
	@Override
	public ExecuteResult<UserLogin> get(String loginId) {
		UserLogin user = userLoginDao.getByLoginId(loginId);
		return ExecuteResult.newSuccessResult(user);
	}
	@Override
	public ExecuteResult<UserLogin> create(CreateSimplePersonLoginUserDto createSimplePersonLoginUserDto) {
		log.info("开始创建登录帐号,loginId={}",createSimplePersonLoginUserDto.getLoginId());
		//帐号重复检查
		try {
			checkArgument(!Strings.isNullOrEmpty(createSimplePersonLoginUserDto.getLoginId()),"登录帐号不能为空");
			checkArgument(!Strings.isNullOrEmpty(createSimplePersonLoginUserDto.getPassword()),"登录密码不能为空");
			checkArgument(createSimplePersonLoginUserDto.getPassword().length()>=6&& createSimplePersonLoginUserDto.getPassword().length()<20,"密码至少6位以上");
			
			UserLogin dbUserLogin = userLoginDao.getByLoginId(createSimplePersonLoginUserDto.getLoginId());
			checkState(dbUserLogin== null,"登录帐号{}已存在",createSimplePersonLoginUserDto.getLoginId());
			
			CreatePersonDto personDto = new CreatePersonDto();
			personDto.setCurrentUser(createSimplePersonLoginUserDto.getCurrentUser());
			personDto.setName(createSimplePersonLoginUserDto.getName());
			personDto.setGender(GenderEnum.N.toString());
			personDto.setMaritalStatus(MaritalStatusEnum.N.toString());
			ExecuteResult<Person> personResult = personService.createPerson(personDto);
			checkState(personResult.isSuccess(),personResult.getError());
			log.info("创建person成功，id={}",personResult.getResult().getId());
			
			UserLogin userLogin = new UserLogin();
			Long id = snowflakeIdWorker.nextId();
			userLogin.setId(id);
			userLogin.setPartyId(personResult.getResult().getId());
			userLogin.setPartyName(personResult.getResult().getName());
			userLogin.setCreatedBy(createSimplePersonLoginUserDto.getCurrentUser());
			userLogin.setUpdatedBy(createSimplePersonLoginUserDto.getCurrentUser());
			userLogin.setEnabled(EnabledEnum.Y.toString());
			userLogin.setLoginId(createSimplePersonLoginUserDto.getLoginId());
			userLogin.setPassword(BCrypt.hashpw(createSimplePersonLoginUserDto.getPassword(), BCrypt.gensalt()));
			
			TransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = partyTransactionManager.getTransaction(def);
			try {
				userLoginDao.insert(userLogin);
				partyTransactionManager.commit(status);
				log.info("success created userLogin:id={}",id);
			} catch (DataAccessException e) {
				log.error("{}",Throwables.getStackTraceAsString(e));
				partyTransactionManager.rollback(status);
	            throw e;
			}
			return ExecuteResult.newSuccessResult(userLogin);
		}catch(IllegalArgumentException | IllegalStateException e) {
			log.warn("input:{},exception:{}",createSimplePersonLoginUserDto,e.getMessage());
			return ExecuteResult.newErrorResult(e.getMessage());
		}
	}
	@Override
	public ExecuteResult<UserLoginHistory> createLoginHistory(CreateUserLoginHistoryDto createUserLoginHistoryDto) {
		log.info("保存用户[{}]登录历史",createUserLoginHistoryDto.getLoginId());
		try {
			checkArgument(createUserLoginHistoryDto.getLoginId()!=null,"loginId不能为空");
			UserLoginHistory history = new UserLoginHistory();
			history.setLoginDate(new  Date());
			history.setLoginId(createUserLoginHistoryDto.getLoginId());
			history.setLoginIp(createUserLoginHistoryDto.getLoginIp());
			Long id = snowflakeIdWorker.nextId();
			history.setId(id);
			userLoginHistoryDao.insert(history);
			log.info("success create user_login_history,login_id={},id={}",createUserLoginHistoryDto.getLoginId(),id);
			return ExecuteResult.newSuccessResult(history);
		}catch(IllegalArgumentException | IllegalStateException e) {
			log.warn("input:{},exception:{}",createUserLoginHistoryDto,e.getMessage());
			return ExecuteResult.newErrorResult(e.getMessage());
		}
	}

}

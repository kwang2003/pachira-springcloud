package com.pachiraframework.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.common.base.Throwables;
import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.dao.PartyDao;
import com.pachiraframework.party.dao.PersonDao;
import com.pachiraframework.party.dto.CreatePersonDto;
import com.pachiraframework.party.entity.PartyType.PartyTypeEnum;
import com.pachiraframework.party.entity.Person;
import com.pachiraframework.party.service.PersonService;
import com.pachiraframework.util.SnowflakeIdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;
	@Autowired
	@Qualifier("partyTransactionManager")
	private PlatformTransactionManager partyTransactionManager;
	@Override
	public ExecuteResult<Person> createPerson(CreatePersonDto createPersonDto) {
		Long partyId = snowflakeIdWorker.nextId();
		log.info("try to create person:id={},name={}",partyId,createPersonDto.getName());
		Person person = new Person();
		person.setId(partyId);
		person.setId(partyId);
		person.setPartyTypeId(PartyTypeEnum.PERSON.toString());
		person.setCreatedBy(createPersonDto.getCurrentUser());
		person.setUpdatedBy(createPersonDto.getCurrentUser());
		person.setDescription(createPersonDto.getName());
		person.setStatusId(createPersonDto.getStatusId());
		person.setBirthDate(createPersonDto.getBirthDate());
		person.setCardId(createPersonDto.getCardId());
		person.setGender(createPersonDto.getGender());
		person.setHeight(createPersonDto.getHeight());
		person.setMaritalStatus(createPersonDto.getMaritalStatus());
		person.setName(createPersonDto.getName());
		person.setPassportExpireDate(createPersonDto.getPassportExpireDate());
		person.setPassportNumber(createPersonDto.getPassportNumber());
		person.setSocialSecurityNumber(createPersonDto.getSocialSecurityNumber());
		person.setWeight(createPersonDto.getWeight());
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = partyTransactionManager.getTransaction(def);
		try {
			partyDao.insert(person);
			personDao.insert(person);
			partyTransactionManager.commit(status);
			log.info("success created person:id={}",partyId);
		} catch (DataAccessException e) {
			log.error("{}",Throwables.getStackTraceAsString(e));
			partyTransactionManager.rollback(status);
            throw e;
		}
		return ExecuteResult.newSuccessResult(person);
	}
	
	@Override
	public ExecuteResult<Person> get(Long id) {
		log.info("id={}",id);
		Person person = personDao.getById(id);
		return ExecuteResult.newSuccessResult(person);
	}

	@Override
	public ExecuteResult<Person> getByCardId(String cardId) {
		log.info("cardId={}",cardId);
		Person person = personDao.getByCardId(cardId);
		return ExecuteResult.newSuccessResult(person);
	}

}

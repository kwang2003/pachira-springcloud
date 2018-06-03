package com.pachiraframework.party.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pachiraframework.common.ExecuteResult;
import com.pachiraframework.party.AbstractPartyTestCase;
import com.pachiraframework.party.dto.CreatePersonDto;
import com.pachiraframework.party.entity.Person;
import com.pachiraframework.party.entity.Person.GenderEnum;
import com.pachiraframework.party.entity.Person.MaritalStatusEnum;
import com.pachiraframework.party.service.PersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
public class PersonServiceImplTest extends AbstractPartyTestCase{
	@Autowired
	private PersonService personService;
	
	@Test
	public void testCreatePerson() {
		CreatePersonDto dto = new CreatePersonDto();
		dto.setBirthDate(new Date());
		dto.setCardId(UUID.randomUUID().toString().substring(0, 18));
		dto.setCurrentUser("大王");
		dto.setGender(GenderEnum.M.toString());
		dto.setHeight(180D);
		dto.setWeight(89D);
		dto.setMaritalStatus(MaritalStatusEnum.M.toString());
		dto.setName("大王102");
		dto.setPassportExpireDate(new Date());
		dto.setPassportNumber("12345566");
		dto.setSocialSecurityNumber("3702");
		dto.setStatusId("PARTY_ENABLED");
		
		ExecuteResult<Person> result = personService.createPerson(dto);
		assertThat(result.isSuccess(),is(true));
	}
	
	@Test
	public void testGet() {
		Long id = 1L;
		ExecuteResult<Person> result = personService.get(id);
		assertThat(result.isSuccess(), is(true));
		Person person = result.getResult();
		assertThat(person, notNullValue());
		assertThat(person.getName(), equalTo("一号首长"));
		assertThat(person.getGender(), equalTo(GenderEnum.M.toString()));
		log.info("{}",person);
	}
	
	@Test
	public void testGetByCardId() {
		String cardId = "123456789";
		ExecuteResult<Person> result = personService.getByCardId(cardId);
		assertThat(result.isSuccess(),is(true));
		Person person = result.getResult();
		assertThat(person, notNullValue());
		assertThat(person.getId(), equalTo(1L));
		assertThat(person.getName(), equalTo("一号首长"));
	}
}

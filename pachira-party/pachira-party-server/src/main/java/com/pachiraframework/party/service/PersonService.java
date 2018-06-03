package com.pachiraframework.party.service;

import com.pachiraframework.party.dto.CreatePersonDto;
import com.pachiraframework.party.entity.Person;

import com.pachiraframework.common.ExecuteResult;

/**
 * 人员相关的service接口
 * @author KevinWang
 *
 */
public interface PersonService {
	/**
	 * 创建一个人员
	 * @param createPersonDto
	 * @return
	 */
	public ExecuteResult<Person> createPerson(CreatePersonDto createPersonDto);
	
	/**
	 * 根据id查询person信息
	 * @param id
	 * @return
	 */
	public ExecuteResult<Person> get(Long id);
	
	/**
	 * 通过身份证号查询
	 * @param cardId
	 * @return
	 */
	public ExecuteResult<Person> getByCardId(String cardId);
}

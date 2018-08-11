package com.pachiraframework.party.service.impl;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KevinWang
 *
 */
@Slf4j
public class BcryptTest {
	@Test
	public void test() {
		log.info(BCrypt.hashpw("123456", BCrypt.gensalt()));
		log.info(BCrypt.hashpw("111111", BCrypt.gensalt()));
	}
}

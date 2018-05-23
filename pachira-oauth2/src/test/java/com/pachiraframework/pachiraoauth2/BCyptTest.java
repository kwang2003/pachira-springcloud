package com.pachiraframework.pachiraoauth2;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BCyptTest {
	@Test
	public void testEncode() {
		for(int i = 0;i < 10;i++) {
			log.info(new BCryptPasswordEncoder().encode("aaaaaaaa"));
		}
		
		log.info(new BCryptPasswordEncoder().encode("123456"));
	}
}

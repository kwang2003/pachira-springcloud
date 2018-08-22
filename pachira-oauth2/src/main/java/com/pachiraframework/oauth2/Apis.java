package com.pachiraframework.oauth2;

/**
 * 系统中用到的api接口
 * 
 * @author KevinWang
 *
 */
public class Apis {
	public static final String PARTY_GATEWAY = "http://localhost:8080";
	public static final String PARTY_CLIENTS = PARTY_GATEWAY + "/v1/party/clients/";
	public static final String PARTY_USERS = PARTY_GATEWAY + "/v1/party/users/";
	public static final String PARTY_USERS_LOGIN_HISTORY = PARTY_GATEWAY + "/v1/party/users/login_history/";
}

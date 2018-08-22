package com.pachiraframework.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.pachiraframework.oauth2.Apis;
import com.pachiraframework.party.api.UserApi;

/**
 * @author wangxuzheng
 *
 */
@FeignClient(name="user-client",url=Apis.PARTY_GATEWAY)
public interface UserClient extends UserApi{
}

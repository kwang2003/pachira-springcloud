package com.pachiraframework.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.pachiraframework.oauth2.Services;
import com.pachiraframework.party.api.UserApi;

/**
 * @author wangxuzheng
 *
 */
@FeignClient(name=Services.PARTY)
public interface UserClient extends UserApi{
}

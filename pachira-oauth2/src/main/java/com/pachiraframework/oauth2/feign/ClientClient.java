package com.pachiraframework.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.pachiraframework.oauth2.Services;
import com.pachiraframework.party.api.ClientApi;

/**
 * @author wangxuzheng
 *
 */
@FeignClient(name=Services.PARTY)
public interface ClientClient extends ClientApi {
}

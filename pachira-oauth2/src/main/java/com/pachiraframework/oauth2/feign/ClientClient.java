package com.pachiraframework.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.pachiraframework.oauth2.Apis;
import com.pachiraframework.oauth2.Services;
import com.pachiraframework.party.api.ClientApi;

/**
 * @author wangxuzheng
 *
 */
@FeignClient(name=Services.PARTY,url=Apis.PARTY_GATEWAY)
public interface ClientClient extends ClientApi {
}

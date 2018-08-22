package com.pachiraframework.oauth2.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.pachiraframework.oauth2.Apis;
import com.pachiraframework.party.api.ClientApi;

/**
 * @author wangxuzheng
 *
 */
@FeignClient(name="client-client",url=Apis.PARTY_GATEWAY)
public interface ClientClient extends ClientApi {
}

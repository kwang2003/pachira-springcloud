package com.pachiraframework.scheduler.component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.pachiraframework.scheduler.entity.Job;

/**
 * 执行http借口
 * @author kevin
 *
 */
@Component
public class HttpJobRunner extends AbstractJobRunner {
	private static final String POST = "POST";
	@Override
	public void runInternel(Job job) throws Exception{
		HttpRequest httpRequest = null;
		String method = job.getMethod();
		if(POST.equalsIgnoreCase(method)) {
			httpRequest = postRequest(job.getInterfaceName(), null);
		}else {
			httpRequest =  getRequest(job.getInterfaceName());
		}
		try {
			httpRequest.readTimeout(job.getTimeout().intValue());
			httpRequest.body();
		}finally {
			httpRequest.disconnect();
		}
	}
	private HttpRequest getRequest(String url){
		HttpRequest httpRequest = HttpRequest.get(url);
		return httpRequest;
	}
	
	private HttpRequest postRequest(String url,String requestParams) throws IOException{
		Map<String, String> param = Maps.newLinkedHashMap();
		if(!Strings.isNullOrEmpty(requestParams)){
			Properties properties = new Properties();
			properties.load(new StringReader(requestParams));
			for(Entry<Object, Object> entry : properties.entrySet()){
				param.put((String)entry.getKey(),(String)entry.getValue());
			}
		}
		HttpRequest httpRequest = HttpRequest.post(url,param,true);
		return httpRequest;
	}
}

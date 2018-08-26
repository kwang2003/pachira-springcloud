package com.pachiraframework.party.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.netflix.hystrix.exception.HystrixBadRequestException;

import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kevin Wang
 *
 */
@Configuration
public class FeignSkipBadRequestsConfiguration {
	private ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();
	@Bean
	public ErrorDecoder errorDecoder() {
		return (methodKey, response) -> {
			int status = response.status();
			if (status == 400) {
				String body = "Bad request";
				try {
					body = Util.toString(response.body().asReader());
				} catch (Exception ignored) {
				}
				HttpHeaders httpHeaders = new HttpHeaders();
				response.headers().forEach((k, v) -> httpHeaders.add("feign-" + k, StringUtils.join(v, ",")));
				return new FeignBadResponseException(status, httpHeaders, body);
			} else {
				return defaultErrorDecoder.decode(methodKey, response);
			}
		};
	}

	@Getter
	@Setter
	public class FeignBadResponseException extends HystrixBadRequestException {
		private static final long serialVersionUID = 7600200029626299672L;
		private final int status;
		private final HttpHeaders headers;
		private final String body;

		public FeignBadResponseException(int status, HttpHeaders headers, String body) {
			super("Bad request");
			this.status = status;
			this.headers = headers;
			this.body = body;
		}
	}
}

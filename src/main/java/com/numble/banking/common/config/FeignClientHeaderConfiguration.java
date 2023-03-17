package com.numble.banking.common.config;

import static com.numble.banking.common.Constant.APP_TYPE_URL_ENCODED;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

public class FeignClientHeaderConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header(HttpHeaders.CONTENT_TYPE, APP_TYPE_URL_ENCODED);
	}
}

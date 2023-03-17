package com.numble.banking.common.config;

import com.numble.banking.exception.ErrorCode;
import com.numble.banking.exception.FeignClientException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class FeignClientExceptionErrorDecoder {

	@Bean
	public ErrorDecoder decoder() {
		return (methodKey, response) -> {
			log.error("{} 요청이 성공하지 못했습니다. request url : {}, status: {}",
				methodKey, response.request().url(), response.status());

			return new FeignClientException(ErrorCode.FEIGN_CLIENT_EXCEPTION);
		};
	}
}

package com.numble.banking.member.oauth.client;

import com.numble.banking.common.config.FeignClientExceptionErrorDecoder;
import com.numble.banking.common.config.FeignClientHeaderConfiguration;
import com.numble.banking.member.oauth.dto.response.OAuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "token-client", url = "${oauth.kakao.oauthServer}", configuration = {
	FeignClientExceptionErrorDecoder.class,
	FeignClientHeaderConfiguration.class
})
public interface OauthTokenClient {

	@PostMapping(value = "/oauth/token", produces = "application/json")
	OAuthTokenResponse getTokens(@RequestParam(value = "code") String code,
		@RequestParam(value = "grant_type") String grantType,
		@RequestParam(value = "client_id") String clientId,
		@RequestParam(value = "redirect_uri") String redirectUri,
		@RequestParam(value = "client_secret") String clientSecret);
}

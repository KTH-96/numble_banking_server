package com.numble.banking.member.oauth.client;

import com.numble.banking.common.config.FeignClientExceptionErrorDecoder;
import com.numble.banking.common.config.FeignClientHeaderConfiguration;
import com.numble.banking.member.oauth.dto.response.KakaoMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "api-client", url = "${oauth.kakao.userInfoServer}", configuration = {
	FeignClientExceptionErrorDecoder.class,
	FeignClientHeaderConfiguration.class,
})
public interface OauthMemberClient {

	@GetMapping("/v2/user/me")
	KakaoMemberInfo getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}

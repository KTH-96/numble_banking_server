package com.numble.banking.member.oauth.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccessTokenResponse {

	private final String accessToken;

	public static AccessTokenResponse from(String accessToken) {
		return new AccessTokenResponse(accessToken);
	}
}

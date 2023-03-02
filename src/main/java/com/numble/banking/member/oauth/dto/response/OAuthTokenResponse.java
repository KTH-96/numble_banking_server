package com.numble.banking.member.oauth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthTokenResponse {

	private String tokenType;

	private String accessToken;

	private int expiresIn;

	private String refreshToken;

	private int refreshTokenExpiresIn;
}

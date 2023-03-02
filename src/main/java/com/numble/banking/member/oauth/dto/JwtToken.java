package com.numble.banking.member.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {

	private String accessToken;
	private String refreshToken;
}

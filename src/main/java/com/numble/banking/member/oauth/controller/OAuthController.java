package com.numble.banking.member.oauth.controller;

import com.numble.banking.member.oauth.dto.JwtToken;
import com.numble.banking.member.oauth.dto.response.AccessTokenResponse;
import com.numble.banking.member.oauth.properties.JwtProperties;
import com.numble.banking.member.oauth.service.OAuthService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

	private final OAuthService oAuthService;
	private final JwtProperties jwtProperties;

	@GetMapping("/login")
	public ResponseEntity<Void> loginPageUrl() {
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
			.header(HttpHeaders.LOCATION, oAuthService.getLoginPageUrl())
			.build();
	}

	@GetMapping("/kakao/callback")
	public ResponseEntity<AccessTokenResponse> loginCallback(
		@RequestParam String code,
		HttpServletResponse response
	) {
		JwtToken token = oAuthService.login(code);
		setCookieRefreshToken(response, token.getRefreshToken());
		return ResponseEntity.ok(AccessTokenResponse.from(token.getAccessToken()));
	}

	private void setCookieRefreshToken(HttpServletResponse response, String refreshToken) {
		ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
			.maxAge(jwtProperties.getRefreshTokenExpiredMillisecond())
			.httpOnly(true)
			.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}

}

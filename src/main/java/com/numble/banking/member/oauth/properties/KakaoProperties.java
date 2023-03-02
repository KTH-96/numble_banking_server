package com.numble.banking.member.oauth.properties;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoProperties {

	@NotEmpty
	private final String loginPageUrl;
	@NotEmpty
	private final String grantType;
	@NotEmpty
	private final String clientId;
	@NotEmpty
	private final String clientSecret;
	@NotEmpty
	private final String redirectUri;
	@NotEmpty
	private final String responseType;

	public KakaoProperties(String loginPageUrl, String grantType, String clientId, String clientSecret,
		String redirectUri, String responseType) {
		this.loginPageUrl = loginPageUrl;
		this.grantType = grantType;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.responseType = responseType;
	}

	public String getLoginPageUrl() {
		return String.format("%s?response_type=%s&client_id=%s&redirect_uri=%s",
			loginPageUrl, responseType, clientId, redirectUri);
	}
}

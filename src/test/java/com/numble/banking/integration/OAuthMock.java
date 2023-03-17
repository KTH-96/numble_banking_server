package com.numble.banking.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.stubbing.Scenario;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class OAuthMock {

	public static void setUpResponse() throws IOException {
		makeMockTokenResponse();
		makeMockOauthTokenWithEmail();
		makeMockOauthTokenWithOutEmail();
	}

	public static void makeMockTokenResponse() {
		stubFor(post(
			urlEqualTo("/oauth/token?code=code&grant_type=authorization_code&"
					+ "client_id=1234&redirect_uri=redirectUri&client_secret=secret"))
			.willReturn(aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("payload/token-response.json")
			)
		);
	}

	public static void makeMockOauthTokenWithEmail() {
		stubFor(get(urlEqualTo("/v2/user/me"))
			.inScenario("has email")
			.whenScenarioStateIs(Scenario.STARTED)
			.withHeader(HttpHeaders.AUTHORIZATION, equalTo("bearer accessToken"))
			.willReturn(aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("payload/member-info-email.json")
			)
		);
	}

	public static void makeMockOauthTokenWithOutEmail() {
		stubFor(get(urlEqualTo("/v2/user/me"))
			.inScenario("has not email")
			.whenScenarioStateIs(Scenario.STARTED)
			.withHeader(HttpHeaders.AUTHORIZATION, equalTo("bearer accessToken"))
			.willReturn(aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("payload/member-info-not-email.json")
			)
		);
	}
}

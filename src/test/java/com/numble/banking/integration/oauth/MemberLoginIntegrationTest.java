package com.numble.banking.integration.oauth;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.integration.OAuthMock;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.repository.MemberRepository;
import com.numble.banking.member.oauth.dto.JwtToken;
import com.numble.banking.member.oauth.service.JwtProvider;
import com.numble.banking.member.oauth.service.OAuthService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = {
	"oauth.kakao.userInfoServer=http://localhost:${wiremock.server.port}",
	"oauth.kakao.oauthServer=http://localhost:${wiremock.server.port}",
})
@DisplayName("OAuth 로그인테스트")
public class MemberLoginIntegrationTest extends InitIntegrationTest {

	@Autowired
	private OAuthService oAuthService;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() throws IOException {
		OAuthMock.setUpResponse();
	}

	@Test
	@DisplayName("로그인 성공 : 이메일 동의 사용자")
	void oauth_login_with_email_success() {
	    WireMock.setScenarioState("has email", "Started");

		JwtToken testToken = oAuthService.login("code");
		Long id = jwtProvider.decodeToken(testToken.getAccessToken());
		Member member = memberRepository.findById(id).get();

		assertThat(testToken.getAccessToken()).isNotNull();
		assertThat(member).isNotNull();
		assertThat(member.getEmail()).isNotNull();
	}

	@Test
	@DisplayName("로그인 성공 : 이메일 비동의 사용자")
	void oauth_login_no_email_success() {
		WireMock.setScenarioState("has not email", "Started");

		JwtToken testToken = oAuthService.login("code");
		Long id = jwtProvider.decodeToken(testToken.getAccessToken());
		Member member = memberRepository.findById(id).get();

		assertThat(testToken.getAccessToken()).isNotNull();
		assertThat(member).isNotNull();
		assertThat(member.getEmail()).isEqualTo("non");
	}
}

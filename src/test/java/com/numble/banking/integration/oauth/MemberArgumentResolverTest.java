package com.numble.banking.integration.oauth;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.common.utils.LoginMemberArgumentResolver;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.oauth.LoginOauthMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

@DisplayName("OAuth Argument Resolver 통합 테스트")
public class MemberArgumentResolverTest extends InitIntegrationTest {

	@Autowired
	private LoginMemberArgumentResolver argumentResolver;
	private MockHttpServletRequest mockHttpServletRequest;

	@BeforeEach
	void setUp() {
		mockHttpServletRequest = new MockHttpServletRequest();
	}

	@Test
	@DisplayName("로그인 된 사용자가 접근할때")
	void login_member() throws Exception {
		mockHttpServletRequest.addHeader(HttpHeaders.AUTHORIZATION, db.member1RefreshToken);

		LoginOauthMember member = (LoginOauthMember)
			argumentResolver.resolveArgument(null, null,
			new ServletWebRequest(mockHttpServletRequest), null);

		assertThat(member.getId()).isEqualTo(db.testMember1.getId());
	}

}

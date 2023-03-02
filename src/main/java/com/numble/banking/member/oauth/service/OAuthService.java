package com.numble.banking.member.oauth.service;

import com.numble.banking.member.Member;
import com.numble.banking.member.member.service.MemberService;
import com.numble.banking.member.oauth.client.OauthWebClient;
import com.numble.banking.member.oauth.dto.JwtToken;
import com.numble.banking.member.oauth.dto.response.KakaoMemberInfo;
import com.numble.banking.member.oauth.dto.response.OAuthTokenResponse;
import com.numble.banking.member.oauth.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthService {

	private final KakaoProperties kakaoProperties;
	private final OauthWebClient oauthWebClient;
	private final MemberService memberService;
	private final JwtProvider jwtProvider;

	public String getLoginPageUrl() {
		return kakaoProperties.getLoginPageUrl();
	}

	@Transactional
	public JwtToken login(String code) {
		OAuthTokenResponse tokens = getOauthTokens(code);
		KakaoMemberInfo memberInfo = getOauthUserInfo(tokens);

		Member member;
		if (!memberService.isExistingBySnsId(memberInfo.getKakaoId())) {
			member = new Member(memberInfo.getKakaoId(), memberInfo.getNickname(),
				memberInfo.getProfileImage(), memberInfo.getEmail());
			memberService.createMember(member);
			return jwtProvider.createToken(member.getId());
		}
		member = memberService.findBySnsId(memberInfo.getKakaoId());
		return jwtProvider.createToken(member.getId());
	}

	private KakaoMemberInfo getOauthUserInfo(OAuthTokenResponse tokens) {
		String authorizationHeader = String.format("%s %s", tokens.getTokenType(),
			tokens.getAccessToken());
		return oauthWebClient.getUserInfo(authorizationHeader);
	}

	private OAuthTokenResponse getOauthTokens(String code) {
		return oauthWebClient.getTokens(code,
				kakaoProperties.getGrantType(),
				kakaoProperties.getClientId(),
				kakaoProperties.getRedirectUri(),
				kakaoProperties.getClientSecret());
	}
}

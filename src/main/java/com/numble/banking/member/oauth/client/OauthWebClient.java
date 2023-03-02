package com.numble.banking.member.oauth.client;

import static com.numble.banking.common.Constant.APP_TYPE_URL_ENCODED;
import static com.numble.banking.common.Constant.BASE_URL;
import static com.numble.banking.common.Constant.CLIENT_ID;
import static com.numble.banking.common.Constant.CLIENT_SECRET;
import static com.numble.banking.common.Constant.CODE;
import static com.numble.banking.common.Constant.GRANT_TYPE;
import static com.numble.banking.common.Constant.KAKAO_TOKEN_URL;
import static com.numble.banking.common.Constant.REDIRECT_URI;
import static com.numble.banking.common.Constant.USER_INFO_URL;

import com.numble.banking.member.oauth.dto.response.KakaoMemberInfo;
import com.numble.banking.member.oauth.dto.response.OAuthTokenResponse;
import javax.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OauthWebClient {

	private WebClient webClient;

	@PostConstruct
	public void initWebClient() {
		webClient = WebClient.create(BASE_URL);
	}


	public OAuthTokenResponse getTokens(String code, String grantType, String clientId,
		String redirectUri, String clientSecret) {
		MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
		params.add(GRANT_TYPE, grantType);
		params.add(CLIENT_ID, clientId );
		params.add(REDIRECT_URI, redirectUri);
		params.add(CODE, code);
		params.add(CLIENT_SECRET, clientSecret);

		return	webClient.post()
					.uri(KAKAO_TOKEN_URL)
					.header(HttpHeaders.CONTENT_TYPE, APP_TYPE_URL_ENCODED)
//					.body(BodyInserters.fromFormData(params))
					.bodyValue(params)
					.retrieve()
					.bodyToMono(OAuthTokenResponse.class)
					.block();
	}

	public KakaoMemberInfo getUserInfo(String authorizationHeader) {
		return webClient.get()
			.uri(USER_INFO_URL)
			.header(HttpHeaders.AUTHORIZATION, authorizationHeader)
			.retrieve()
			.bodyToMono(KakaoMemberInfo.class)
			.block();
	}
}

package com.numble.banking.common.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.numble.banking.member.oauth.dto.response.KakaoMemberInfo;
import java.io.IOException;

public class KakaoMemberInfoDeserializer extends JsonDeserializer {

	@Override
	public KakaoMemberInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		JsonNode jsonNode = p.getCodec().readTree(p);

		long kakaoId = jsonNode.get("id").asLong();
		JsonNode kakaoAccount = jsonNode.get("kakao_account");
		JsonNode profile = kakaoAccount.get("profile");

		String nickname = profile.get("nickname").asText();
		String profileImageUrl = profile.get("profile_image_url").asText();

		if (kakaoAccount.has("email")) {
			String email = kakaoAccount.get("email").asText();
			boolean isEmailValid = kakaoAccount.get("is_email_valid").asBoolean();
			boolean isEmailVerified = kakaoAccount.get("is_email_verified").asBoolean();

			if (isEmailValid && isEmailVerified) {
				return new KakaoMemberInfo(kakaoId, nickname, profileImageUrl, email);
			}
		}
		return new KakaoMemberInfo(kakaoId, nickname, profileImageUrl, "non");
	}
}

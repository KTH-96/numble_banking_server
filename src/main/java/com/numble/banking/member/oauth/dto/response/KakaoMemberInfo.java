package com.numble.banking.member.oauth.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.numble.banking.common.deserializer.KakaoMemberInfoDeserializer;
import lombok.Getter;
import lombok.ToString;

@JsonDeserialize(using = KakaoMemberInfoDeserializer.class)
@Getter
@ToString
public class KakaoMemberInfo {

	private Long kakaoId;
	private String nickname;
	private String profileImage;
	private String email;

	public KakaoMemberInfo(long kakaoId, String nickname, String profileImage, String email) {
		this.kakaoId = kakaoId;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.email = email;
	}
}

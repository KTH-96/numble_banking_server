package com.numble.banking.member.oauth;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginOauthMember {

	@NotNull
	private Long id;

	public static LoginOauthMember from(Long id) {
		return new LoginOauthMember(id);
	}
}

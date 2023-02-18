package com.numble.banking.member.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberSignUpResponse {

	private final String name;
	private final String accountNumber;

	public static MemberSignUpResponse from(String name, String accountNumber) {
		return new MemberSignUpResponse(name, accountNumber);
	}
}

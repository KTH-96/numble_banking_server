package com.numble.banking.member.dto.response;

import com.numble.banking.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberSignInResponse {

	private final Long memberId;
	private final String name;
	private final String accountNumber;

	public static MemberSignInResponse of(Member signInMember) {
		return new MemberSignInResponse(signInMember.getId(),
			signInMember.getName(),
			signInMember.findBasicAccountNumber());
	}
}

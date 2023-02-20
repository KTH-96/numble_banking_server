package com.numble.banking.member.dto.response;

import com.numble.banking.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LogoutMemberResponse {

	private final String name;

	public static LogoutMemberResponse from(Member member) {
		return new LogoutMemberResponse(member.getName());
	}
}

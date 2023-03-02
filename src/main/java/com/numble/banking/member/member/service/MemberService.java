package com.numble.banking.member.member.service;

import com.numble.banking.member.Member;

public interface MemberService {

	boolean isExistingBySnsId(Long kakaoId);

	void createMember(Member member);

	Member findBySnsId(Long kakaoId);

	Member findById(Long loginMemberId);
}

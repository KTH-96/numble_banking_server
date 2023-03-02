package com.numble.banking.member.member.service;

import com.numble.banking.account.Account;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.exception.ErrorCode;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.exception.NotFindMemberException;
import com.numble.banking.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final AccountService accountService;

	@Override
	public Member findById(Long loginMemberId) {
		return memberRepository.findById(loginMemberId).orElseThrow(() ->
			new NotFindMemberException(ErrorCode.NOT_FIND_MEMBER));
	}

	@Override
	public Member findBySnsId(Long kakaoId) {
		return memberRepository.findBySnsId(kakaoId).orElseThrow(() ->
			new NotFindMemberException(ErrorCode.NOT_FIND_MEMBER));
	}

	@Override
	@Transactional
	public void createMember(Member member) {
		Account account = accountService.createAccount();
		account.changeMember(member);
		memberRepository.save(member);
	}

	@Override
	public boolean isExistingBySnsId(Long kakaoId) {
		return memberRepository.existsBySnsId(kakaoId);
	}

}

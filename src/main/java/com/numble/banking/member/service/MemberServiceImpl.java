package com.numble.banking.member.service;

import com.numble.banking.account.Account;
import com.numble.banking.member.Member;
import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.response.MemberSignUpResponse;
import com.numble.banking.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;

	@Transactional
	@Override
	public MemberSignUpResponse signUp(MemberSignUpRequest signUpMember) {
		Member member = Member.createMember(signUpMember);
		Account account = Account.createAccount();
		account.changeMember(member);

		repository.save(member);

		return MemberSignUpResponse.from(member.getName(), account.getNumber());
	}
}

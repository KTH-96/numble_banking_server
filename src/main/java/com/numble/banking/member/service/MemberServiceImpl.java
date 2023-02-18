package com.numble.banking.member.service;

import com.numble.banking.account.Account;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.exception.ErrorCode;
import com.numble.banking.exception.MemberDuplicationException;
import com.numble.banking.member.Member;
import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.response.MemberSignUpResponse;
import com.numble.banking.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final AccountService accountService;

	@Transactional
	@Override
	public MemberSignUpResponse signUp(MemberSignUpRequest signUpMember) {
		checkDuplicationMember(signUpMember);
		Member member = Member.createMember(signUpMember);
		Account account = accountService.createAccount();
		account.changeMember(member);

		memberRepository.save(member);

		return MemberSignUpResponse.from(member.getName(), account.getNumber());
	}

	private void checkDuplicationMember(MemberSignUpRequest signUpMember) {
		Optional<Boolean> duplication = memberRepository.existsMembersByEmail(signUpMember.getEmail());
		if (duplication.orElse(false)) {
			throw new MemberDuplicationException(ErrorCode.DUPLICATION_MEMBER_EMAIL);
		}
	}
}

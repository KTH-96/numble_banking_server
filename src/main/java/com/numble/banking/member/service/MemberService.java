package com.numble.banking.member.service;

import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.response.MemberSignUpResponse;

public interface MemberService {

	MemberSignUpResponse signUp(MemberSignUpRequest signUpMember);
}
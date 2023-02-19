package com.numble.banking.member.service;

import com.numble.banking.member.dto.request.LoginMember;
import com.numble.banking.member.dto.request.MemberSignInRequest;
import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.response.LogoutMemberResponse;
import com.numble.banking.member.dto.response.MemberSignInResponse;
import com.numble.banking.member.dto.response.MemberSignUpResponse;

public interface MemberService {

	MemberSignUpResponse signUp(MemberSignUpRequest signUpMember);

	MemberSignInResponse singIn(MemberSignInRequest signInRequest);

	LogoutMemberResponse logout(LoginMember loginMember);
}

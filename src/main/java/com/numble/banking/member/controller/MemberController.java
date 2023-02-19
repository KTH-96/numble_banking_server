package com.numble.banking.member.controller;

import static com.numble.banking.common.Constant.LOGIN_MEMBER;
import static com.numble.banking.exception.ErrorCode.LOGOUT_FAIL_EXCEPTION;

import com.numble.banking.exception.LogoutFailException;
import com.numble.banking.member.dto.request.MemberSignInRequest;
import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.request.LoginMember;
import com.numble.banking.member.dto.response.LogoutMemberResponse;
import com.numble.banking.member.dto.response.MemberSignInResponse;
import com.numble.banking.member.dto.response.MemberSignUpResponse;
import com.numble.banking.member.service.MemberServiceImpl;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberServiceImpl memberService;

	@PostMapping("/signup")
	public ResponseEntity<MemberSignUpResponse> signUp(
		@RequestBody @Valid MemberSignUpRequest signUpMember
	) {
		MemberSignUpResponse response = memberService.signUp(signUpMember);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/signin")
	public ResponseEntity<MemberSignInResponse> singIn(
		@RequestBody @Valid MemberSignInRequest signInRequest,
		HttpServletRequest request
	) {
		MemberSignInResponse singInMember = memberService.singIn(signInRequest);
		HttpSession session = request.getSession();
		session.setAttribute(LOGIN_MEMBER, singInMember);
		return ResponseEntity.status(HttpStatus.OK).body(singInMember);
	}

	@PostMapping("/logout")
	public ResponseEntity<LogoutMemberResponse> logout(
		@RequestBody @Valid LoginMember loginMember,
		HttpServletRequest request
	) {
		LogoutMemberResponse response = memberService.logout(loginMember);
		HttpSession session = Optional.of(request.getSession(false))
			.orElseThrow(() -> new LogoutFailException(LOGOUT_FAIL_EXCEPTION));
		session.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

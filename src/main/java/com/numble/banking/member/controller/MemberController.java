package com.numble.banking.member.controller;

import com.numble.banking.member.dto.request.MemberSignUpRequest;
import com.numble.banking.member.dto.response.MemberSignUpResponse;
import com.numble.banking.member.service.MemberServiceImpl;
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
	public ResponseEntity<MemberSignUpResponse> signUp(@RequestBody MemberSignUpRequest signUpMember) {
		MemberSignUpResponse response = memberService.signUp(signUpMember);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}

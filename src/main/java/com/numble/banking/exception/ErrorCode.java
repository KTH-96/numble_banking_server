package com.numble.banking.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {

	NOT_FIND_MEMBER("N001", HttpStatus.BAD_REQUEST, "가입한 회원이 아닙니다."),
	NOT_MATCH_PASSWORD("N002", HttpStatus.BAD_REQUEST, "맞지않는 비밀번호 입니다."),
	DUPLICATION_MEMBER_EMAIL("M001", HttpStatus.CONFLICT, "중복된 이메일입니다."),
	INVALID_INPUT_VALUE("R001", HttpStatus.BAD_REQUEST,"요청값이 올바르지 않습니다.");

	private final String code;
	private final HttpStatus status;
	private final String message;
}

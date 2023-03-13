package com.numble.banking.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {

	NOT_FIND_MEMBER("N001", HttpStatus.BAD_REQUEST, "가입한 회원이 아닙니다."),
	NOT_FIND_ACCOUNT("N002", HttpStatus.BAD_REQUEST, "계좌번호가 존재하지 않습니다."),
	NOT_MATCH_PASSWORD("N003", HttpStatus.BAD_REQUEST, "맞지않는 비밀번호 입니다."),
	NOT_FIND_FRIEND("N004", HttpStatus.BAD_REQUEST, "친구가 존재하지 않습니다."),
	NOT_EMPTY_MONEY("N005", HttpStatus.BAD_REQUEST, "잔고가 부족합니다."),
	TOO_MUCH_MONEY("N005", HttpStatus.BAD_REQUEST, "잔고가 너무 많습니다."),
	DUPLICATION_MEMBER_EMAIL("M001", HttpStatus.CONFLICT, "중복된 이메일입니다."),
	LOGOUT_FAILED("M002", HttpStatus.BAD_REQUEST, "로그인 실패"),
	NOT_LOGIN_STATUS("M003", HttpStatus.BAD_REQUEST, "로그인이 필요합니다."),
	INVALID_INPUT_VALUE("R001", HttpStatus.BAD_REQUEST, "요청값이 올바르지 않습니다."),

	FEIGN_CLIENT_EXCEPTION("F001", HttpStatus.BAD_REQUEST, "FEIGN_CLIENT 에러"),
	NOT_WEB_REQUEST_EXCEPTION("F002", HttpStatus.BAD_REQUEST, "WEB_REQUEST 에러");

	private final String code;
	private final HttpStatus status;
	private final String message;
}

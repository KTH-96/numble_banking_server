package com.numble.banking.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {

	INVALID_INPUT_VALUE("R001", HttpStatus.BAD_REQUEST,"요청값이 올바르지 않습니다.");

	private final String code;
	private final HttpStatus status;
	private final String message;
}

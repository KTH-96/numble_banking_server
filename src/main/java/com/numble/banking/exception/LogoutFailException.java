package com.numble.banking.exception;

public class LogoutFailException extends ApplicationException {

	public LogoutFailException(ErrorCode errorCode) {
		super(errorCode);
	}
}

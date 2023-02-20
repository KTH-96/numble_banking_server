package com.numble.banking.exception;

public class NotFindAccountException extends ApplicationException {

	public NotFindAccountException(ErrorCode errorCode) {
		super(errorCode);
	}
}

package com.numble.banking.exception;

public class NotRefreshTokenException extends ApplicationException {

	public NotRefreshTokenException(ErrorCode errorCode) {
		super(errorCode);
	}
}

package com.numble.banking.exception;

public class NotSessionException extends ApplicationException {

	public NotSessionException(ErrorCode errorCode) {
		super(errorCode);
	}
}

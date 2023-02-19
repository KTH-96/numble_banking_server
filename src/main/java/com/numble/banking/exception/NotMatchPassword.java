package com.numble.banking.exception;

public class NotMatchPassword extends ApplicationException {

	public NotMatchPassword(ErrorCode errorCode) {
		super(errorCode);
	}
}

package com.numble.banking.exception;

public class WebRequestException extends ApplicationException {

	public WebRequestException(ErrorCode errorCode) {
		super(errorCode);
	}
}

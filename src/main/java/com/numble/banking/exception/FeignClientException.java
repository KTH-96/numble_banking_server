package com.numble.banking.exception;

public class FeignClientException extends ApplicationException{

	public FeignClientException(ErrorCode errorCode) {
		super(errorCode);
	}
}

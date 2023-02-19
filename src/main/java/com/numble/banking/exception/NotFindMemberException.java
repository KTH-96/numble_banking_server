package com.numble.banking.exception;

public class NotFindMemberException extends ApplicationException{

	public NotFindMemberException(ErrorCode errorCode) {
		super(errorCode);
	}
}

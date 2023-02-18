package com.numble.banking.exception;

public class MemberDuplicationException extends ApplicationException {

	public MemberDuplicationException(ErrorCode errorCode) {
		super(errorCode);
	}
}

package com.numble.banking.member.member.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class NotFindMemberException extends ApplicationException {

	public NotFindMemberException(ErrorCode errorCode) {
		super(errorCode);
	}
}

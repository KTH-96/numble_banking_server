package com.numble.banking.member.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class LogoutFailException extends ApplicationException {

	public LogoutFailException(ErrorCode errorCode) {
		super(errorCode);
	}
}

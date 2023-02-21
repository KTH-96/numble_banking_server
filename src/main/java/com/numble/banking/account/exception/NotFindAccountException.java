package com.numble.banking.account.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class NotFindAccountException extends ApplicationException {

	public NotFindAccountException(ErrorCode errorCode) {
		super(errorCode);
	}
}

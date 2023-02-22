package com.numble.banking.account.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class NotEmptyMoneyException extends ApplicationException {

	public NotEmptyMoneyException(ErrorCode errorCode) {
		super(errorCode);
	}
}

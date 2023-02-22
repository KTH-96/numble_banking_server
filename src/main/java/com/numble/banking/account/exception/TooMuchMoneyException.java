package com.numble.banking.account.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class TooMuchMoneyException extends ApplicationException {

	public TooMuchMoneyException(ErrorCode errorCode) {
		super(errorCode);
	}
}

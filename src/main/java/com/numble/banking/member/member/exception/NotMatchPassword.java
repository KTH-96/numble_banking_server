package com.numble.banking.member.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class NotMatchPassword extends ApplicationException {

	public NotMatchPassword(ErrorCode errorCode) {
		super(errorCode);
	}
}

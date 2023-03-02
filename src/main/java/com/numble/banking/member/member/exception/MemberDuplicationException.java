package com.numble.banking.member.member.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class MemberDuplicationException extends ApplicationException {

	public MemberDuplicationException(ErrorCode errorCode) {
		super(errorCode);
	}
}

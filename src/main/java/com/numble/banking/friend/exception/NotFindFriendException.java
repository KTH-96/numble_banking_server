package com.numble.banking.friend.exception;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.ErrorCode;

public class NotFindFriendException extends ApplicationException {

	public NotFindFriendException(ErrorCode errorCode) {
		super(errorCode);
	}
}

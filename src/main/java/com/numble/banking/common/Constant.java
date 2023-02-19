package com.numble.banking.common;

public interface Constant {

	// 계좌번호
	int ACCOUNT_NUMBER_LENGTH = 16;
	int ACCOUNT_NUMBER_PREFIX = 1000000;
	int ACCOUNT_NUMBER_MID = 100;
	int ACCOUNT_NUMBER_SUFFIX = 1000000;

	int BASIC_ACCOUNT = 0;
	String ACCOUNT_NUMBER_PATTERN = "\\d{6}-\\d{2}-\\d{6}";
	String DECIMAL_PATTERN = "0";
	String HYPHEN = "-";

	// 로그인
	String LOGIN_MEMBER = "loginMember";

}

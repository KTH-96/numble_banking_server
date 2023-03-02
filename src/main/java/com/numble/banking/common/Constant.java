package com.numble.banking.common;

public interface Constant {

	// 계좌번호
	int ACCOUNT_NUMBER_LENGTH = 16;
	int ACCOUNT_NUMBER_PREFIX = 1000000;
	int ACCOUNT_NUMBER_MID = 100;
	int ACCOUNT_NUMBER_SUFFIX = 1000000;

	int BASIC_ACCOUNT = 0;
	int ZERO = 0;
	String ACCOUNT_NUMBER_PATTERN = "\\d{6}-\\d{2}-\\d{6}";
	String DECIMAL_PATTERN = "0";
	String HYPHEN = "-";

	// 로그인
	String LOGIN_MEMBER = "loginMember";

	//web client
	String BASE_URL = "http://localhost:8080";
	String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
	String APP_TYPE_URL_ENCODED = "application/x-www-form-urlencoded;charset=utf-8";
	String GRANT_TYPE = "grant_type";
	String CLIENT_ID = "client_id";
	String REDIRECT_URI = "redirect_uri";
	String CODE = "code";
	String CLIENT_SECRET = "client_secret";
}

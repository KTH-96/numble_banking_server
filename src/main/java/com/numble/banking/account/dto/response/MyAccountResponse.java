package com.numble.banking.account.dto.response;

import com.numble.banking.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MyAccountResponse {

	private final Page<AccountSingleResponse> singleAccounts;

	public static MyAccountResponse from(Page<Account> accounts) {
		return new MyAccountResponse(accounts.map(AccountSingleResponse::from));
	}
}

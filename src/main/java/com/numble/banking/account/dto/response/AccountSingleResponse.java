package com.numble.banking.account.dto.response;

import com.numble.banking.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountSingleResponse {

	private final Long accountId;
	private final String accountNumber;
	private final Long currentMoney;

	public static AccountSingleResponse from(Account account) {
		return new AccountSingleResponse(account.getId(),
			account.getNumber(),
			account.getMoney()
		);
	}

}

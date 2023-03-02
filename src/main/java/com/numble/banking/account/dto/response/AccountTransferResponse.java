package com.numble.banking.account.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountTransferResponse {

	private final Long memberId;
	private final Long currentMyMoney;
	private final String friendAccountNumber;

	public static AccountTransferResponse from(Long memberId, Long money, String number) {
		return new AccountTransferResponse(memberId, money, number);
	}
}

package com.numble.banking.account.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountTransferRequest {

	@NotBlank
	private String MyAccountNumber;

	@NotBlank
	private String friendAccountNumber;

	@Positive
	private Long transferMoney;

	public static AccountTransferRequest from(String myAccountNumber,
		String friendAccountNumber, Long transferMoney) {
		return new AccountTransferRequest(myAccountNumber,
			friendAccountNumber, transferMoney);
	}
}

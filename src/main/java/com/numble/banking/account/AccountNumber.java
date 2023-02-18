package com.numble.banking.account;

import java.text.DecimalFormat;
import java.util.Random;

public class AccountNumber {

	private final String accountNumber;

	public AccountNumber() {
		this.accountNumber = createAccountNumber();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	private String createAccountNumber() {
		Random random = new Random();
		int pre = random.nextInt(1000000);
		int min = random.nextInt(100);
		int bac = random.nextInt(1000000);
		String hyphen = "-";
		DecimalFormat df = new DecimalFormat("0");

		return df.format(pre) + hyphen + df.format(min) + hyphen + df.format(bac);
	}
}

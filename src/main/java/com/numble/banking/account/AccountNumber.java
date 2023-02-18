package com.numble.banking.account;

import java.text.DecimalFormat;
import java.util.Random;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountNumber {

	@Size(min = 16, max = 16)
	@Pattern(regexp = "\\d{6}-\\d{2}-\\d{6}")
	private final String accountNumber;

	public static String createAccountNumber() {
		Random random = new Random();
		int pre = random.nextInt(1000000);
		int min = random.nextInt(100);
		int bac = random.nextInt(1000000);
		String hyphen = "-";
		DecimalFormat df = new DecimalFormat("0");
		String accountNumber = df.format(pre) + hyphen + df.format(min) + hyphen + df.format(bac);
		return String.valueOf(new AccountNumber(accountNumber).getAccountNumber());
	}
}

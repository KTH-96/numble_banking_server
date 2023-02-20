package com.numble.banking.account;

import static com.numble.banking.common.Constant.ACCOUNT_NUMBER_LENGTH;
import static com.numble.banking.common.Constant.ACCOUNT_NUMBER_MID;
import static com.numble.banking.common.Constant.ACCOUNT_NUMBER_PATTERN;
import static com.numble.banking.common.Constant.ACCOUNT_NUMBER_PREFIX;
import static com.numble.banking.common.Constant.ACCOUNT_NUMBER_SUFFIX;
import static com.numble.banking.common.Constant.DECIMAL_PATTERN;
import static com.numble.banking.common.Constant.HYPHEN;

import java.text.DecimalFormat;
import java.util.Random;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountNumber {

	@Size(min = ACCOUNT_NUMBER_LENGTH, max = ACCOUNT_NUMBER_LENGTH)
	@Pattern(regexp = ACCOUNT_NUMBER_PATTERN)
	private final String accountNumber;

	public static String createAccountNumber() {
		Random random = new Random();
		int pre = random.nextInt(ACCOUNT_NUMBER_PREFIX);
		int min = random.nextInt(ACCOUNT_NUMBER_MID);
		int bac = random.nextInt(ACCOUNT_NUMBER_SUFFIX);
		return makeAccountNumber(pre, min, bac);
	}

	private static String makeAccountNumber(int pre, int min, int bac) {
		DecimalFormat df = new DecimalFormat(DECIMAL_PATTERN);
		String accountNumber = df.format(pre) + HYPHEN + df.format(min) + HYPHEN + df.format(bac);
		return String.valueOf(new AccountNumber(accountNumber).getAccountNumber());
	}
}

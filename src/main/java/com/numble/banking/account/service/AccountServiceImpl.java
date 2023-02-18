package com.numble.banking.account.service;

import com.numble.banking.account.Account;
import com.numble.banking.account.AccountNumber;
import com.numble.banking.account.repository.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository accountRepository;

	@Transactional
	@Override
	public Account createAccount() {
		String accountNumber = AccountNumber.createAccountNumber();
		Optional<Boolean> duplication = accountRepository.existsByNumber(accountNumber);

		return makeAccount(accountNumber, duplication);
	}

	private Account makeAccount(String accountNumber, Optional<Boolean> duplication) {
		while (duplication.orElse(false)){
			accountNumber = AccountNumber.createAccountNumber();
			duplication = accountRepository.existsByNumber(accountNumber);
		}

		return Account.createAccount(accountNumber);
	}
}

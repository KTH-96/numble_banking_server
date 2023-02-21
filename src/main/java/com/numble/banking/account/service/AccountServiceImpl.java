package com.numble.banking.account.service;

import static com.numble.banking.exception.ErrorCode.NOT_FIND_ACCOUNT;

import com.numble.banking.account.Account;
import com.numble.banking.account.AccountNumber;
import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.account.repository.AccountRepository;
import com.numble.banking.account.exception.NotFindAccountException;
import com.numble.banking.member.dto.LoginMember;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Page<AccountResponse> findMyAccounts(LoginMember loginMember, Pageable pageable) {
		Page<Account> accounts = accountRepository.findAccountById(loginMember.getId(), pageable);
		if (accounts.isEmpty()) {
			throw new NotFindAccountException(NOT_FIND_ACCOUNT);
		}
		return accounts.map(AccountResponse::from);
	}

	@Transactional
	@Override
	public Account createAccount() {
		String accountNumber = AccountNumber.createAccountNumber();
		Optional<Boolean> duplication = accountRepository.existsByNumber(accountNumber);

		return makeAccount(accountNumber, duplication);
	}

	private Account makeAccount(String accountNumber, Optional<Boolean> duplication) {
		while (duplication.orElse(false)) {
			accountNumber = AccountNumber.createAccountNumber();
			duplication = accountRepository.existsByNumber(accountNumber);
		}

		return Account.createAccount(accountNumber);
	}
}

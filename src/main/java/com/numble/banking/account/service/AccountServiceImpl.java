package com.numble.banking.account.service;

import static com.numble.banking.exception.ErrorCode.NOT_FIND_ACCOUNT;

import com.numble.banking.account.Account;
import com.numble.banking.account.AccountNumber;
import com.numble.banking.account.dto.request.AccountTransferRequest;
import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.account.dto.response.AccountTransferResponse;
import com.numble.banking.account.exception.NotFindAccountException;
import com.numble.banking.account.repository.AccountRepository;
import com.numble.banking.member.member.service.MemberService;
import com.numble.banking.mock.NumbleAlarmService;
import javax.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	public AccountTransferResponse transferMoney(Long loginMemberId, AccountTransferRequest request) {
		Account myAccount = accountRepository.findByNumber(request.getMyAccountNumber())
			.orElseThrow(() -> new NotFindAccountException(NOT_FIND_ACCOUNT));

		Account friendAccount = accountRepository.findByNumber(request.getFriendAccountNumber())
			.orElseThrow(() -> new NotFindAccountException(NOT_FIND_ACCOUNT));

		myAccount.minusMoney(request.getTransferMoney());
		friendAccount.plusMoney(request.getTransferMoney());

		NumbleAlarmService.notify(myAccount.getMoney(), friendAccount.getNumber());

		return AccountTransferResponse.from(loginMemberId, myAccount.getMoney(), friendAccount.getNumber());
	}

	@Override
	public Page<AccountResponse> findMyAccounts(Long loginMemberId, Pageable pageable) {
		Page<Account> accounts = accountRepository.findAccountById(loginMemberId, pageable);
		if (accounts.isEmpty()) {
			throw new NotFindAccountException(NOT_FIND_ACCOUNT);
		}
		return accounts.map(AccountResponse::from);
	}

	@Transactional
	@Override
	public Account createAccount() {
		String accountNumber = AccountNumber.createAccountNumber();
		boolean duplication = accountRepository.existsByNumber(accountNumber);

		return makeAccount(accountNumber, duplication);
	}

	private Account makeAccount(String accountNumber, boolean duplication) {
		while (duplication) {
			accountNumber = AccountNumber.createAccountNumber();
			duplication = accountRepository.existsByNumber(accountNumber);
		}

		return Account.createAccount(accountNumber);
	}
}

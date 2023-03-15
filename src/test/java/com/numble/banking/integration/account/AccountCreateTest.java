package com.numble.banking.integration.account;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.account.Account;
import com.numble.banking.account.repository.AccountRepository;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.integration.InitIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("계좌생성 통합 테스트")
public class AccountCreateTest extends InitIntegrationTest {

	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@Test
	@DisplayName("계좌생성 성공 테스트")
	void account_create_success_test() {
	    //given 준비
		Account account = accountService.createAccount();
		Account saveAccount = accountRepository.save(account);
		//then 검증
		assertThat(saveAccount.getId()).isNotNull();
		assertThat(saveAccount.getMoney()).isEqualTo(0L);
		assertThat(saveAccount.getNumber()).isNotNull();
	}

	@Test
	@DisplayName("계좌번호가 이미 존재하는지 테스트")
	void account_exists_number_test() {
		//given 준비
		Account account = accountService.createAccount();
		accountRepository.save(account);
		boolean exists = accountRepository.existsByNumber(account.getNumber());
		//then 검증
		assertThat(exists).isTrue();
	}
}

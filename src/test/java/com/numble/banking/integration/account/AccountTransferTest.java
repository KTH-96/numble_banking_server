package com.numble.banking.integration.account;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.account.dto.request.AccountTransferRequest;
import com.numble.banking.account.dto.response.AccountTransferResponse;
import com.numble.banking.account.exception.NotEmptyMoneyException;
import com.numble.banking.account.exception.NotFindAccountException;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("계좌이체 통합 테스트")
public class AccountTransferTest extends InitIntegrationTest {

	@Autowired
	private AccountService accountService;

	@Test
	@DisplayName("계좌이체 성공 테스트")
	void account_transfer_success_test() {
	    //given 준비
		Member member1 = db.testMember1;
		String member1AccountNumber = member1.findBasicAccountNumber();
		Member member2 = db.testMember2;
		String member2AccountNumber = member2.findBasicAccountNumber();
		AccountTransferRequest transferRequest =
			AccountTransferRequest.from(member1AccountNumber, member2AccountNumber, 10000L);
		//when 실행
		AccountTransferResponse transferResponse = accountService.transferMoney(member1.getId(),
			transferRequest);
		//then 검증
		assertThat(transferResponse.getCurrentMyMoney()).isEqualTo(10000L);
	}

	@Test
	@DisplayName("계좌이체 실패 테스트(돈 부족)")
	void account_transfer_money_fail_test() {
		//given 준비
		Member member1 = db.testMember1;
		String member1AccountNumber = member1.findBasicAccountNumber();
		Member member2 = db.testMember2;
		String member2AccountNumber = member2.findBasicAccountNumber();
		AccountTransferRequest transferRequest =
			AccountTransferRequest.from(member1AccountNumber, member2AccountNumber, 30000L);
		//when 실행
		//then 검증
		assertThatThrownBy(() -> accountService.transferMoney(member1.getId(), transferRequest))
			.isInstanceOf(NotEmptyMoneyException.class);
	}

	@Test
	@DisplayName("계좌이체 실패 테스트(내 계좌 못찾음)")
	void account_transfer_my_account_fail_test() {
		//given 준비
		Member member1 = db.testMember1;
		String member1AccountNumber = "testAccount1";
		Member member2 = db.testMember2;
		String member2AccountNumber = member2.findBasicAccountNumber();
		AccountTransferRequest transferRequest =
			AccountTransferRequest.from(member1AccountNumber, member2AccountNumber, 10000L);
		//when 실행
		//then 검증
		assertThatThrownBy(() -> accountService.transferMoney(member1.getId(), transferRequest))
			.isInstanceOf(NotFindAccountException.class);
	}

	@Test
	@DisplayName("계좌이체 실패 테스트(친구 계좌 못찾음)")
	void account_transfer_friend_account_fail_test() {
		//given 준비
		Member member1 = db.testMember1;
		String member1AccountNumber = member1.findBasicAccountNumber();
		String member2AccountNumber = "testAccount2";
		AccountTransferRequest transferRequest =
			AccountTransferRequest.from(member1AccountNumber, member2AccountNumber, 10000L);
		//when 실행
		//then 검증
		assertThatThrownBy(() -> accountService.transferMoney(member1.getId(), transferRequest))
			.isInstanceOf(NotFindAccountException.class);
	}
}

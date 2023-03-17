package com.numble.banking.integration.account;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.account.exception.NotFindAccountException;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DisplayName("계좌찾기 통합 테스트")
public class AccountFindTest extends InitIntegrationTest {

	@Autowired
	private AccountService accountService;

	@Test
	@DisplayName("내 계좌찾기 성공 테스트")
	void my_account_find_success_test() {
	    //given 준비
		Member member = db.testMember1;
		PageRequest pageRequest = PageRequest.of(0, 5);
		//when 실행
		Page<AccountResponse> myAccounts = accountService.findMyAccounts(member.getId(), pageRequest);
		//then 검증
		assertThat(member.getAccounts().size()).isEqualTo(myAccounts.getContent().size());
	}

	@Test
	@DisplayName("내 계좌찾기 실패 테스트")
	void my_account_find_fail_test() {
	    //given 준비
		Long id = Long.MIN_VALUE;
		PageRequest pageRequest = PageRequest.of(0, 5);

		assertThatThrownBy(() -> accountService.findMyAccounts(id, pageRequest))
			.isInstanceOf(NotFindAccountException.class);
	}

}

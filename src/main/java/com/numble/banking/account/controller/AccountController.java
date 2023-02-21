package com.numble.banking.account.controller;

import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.member.dto.LoginMember;
import com.numble.banking.common.utils.Login;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/my_accounts")
	public Page<AccountResponse> findMyAccounts(
		@Login @Valid LoginMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return accountService.findMyAccounts(loginMember, pageable);
	}
}

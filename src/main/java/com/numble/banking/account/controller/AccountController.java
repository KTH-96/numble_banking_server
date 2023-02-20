package com.numble.banking.account.controller;

import com.numble.banking.account.dto.response.MyAccountResponse;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.member.dto.LoginMember;
import com.numble.banking.utils.Login;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/my_accounts")
	public MyAccountResponse findMyAccounts(
		@Login @Valid LoginMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return accountService.findMyAccounts(loginMember, pageable);
	}
}

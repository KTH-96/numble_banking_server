package com.numble.banking.account.controller;

import com.numble.banking.account.dto.request.AccountTransferRequest;
import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.account.dto.response.AccountTransferResponse;
import com.numble.banking.account.service.AccountService;
import com.numble.banking.common.utils.LoginOauth;
import com.numble.banking.member.oauth.LoginOauthMember;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/my")
	public Page<AccountResponse> findMyAccounts(
		@LoginOauth @Valid LoginOauthMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return accountService.findMyAccounts(loginMember.getId(), pageable);
	}

	@PostMapping("/transfer")
	private AccountTransferResponse transfer(
		@LoginOauth @Valid LoginOauthMember loginMember,
		@RequestBody @Valid AccountTransferRequest request
	) {
		return accountService.transferMoney(loginMember.getId(), request);
	}
}

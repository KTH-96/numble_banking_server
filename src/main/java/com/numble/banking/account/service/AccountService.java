package com.numble.banking.account.service;

import com.numble.banking.account.Account;
import com.numble.banking.account.dto.response.AccountResponse;
import com.numble.banking.member.dto.LoginMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

	Account createAccount();

	Page<AccountResponse> findMyAccounts(LoginMember loginMember, Pageable pageable);
}

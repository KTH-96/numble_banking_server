package com.numble.banking.account.service;

import com.numble.banking.account.Account;
import com.numble.banking.account.dto.response.MyAccountResponse;
import com.numble.banking.member.dto.LoginMember;
import org.springframework.data.domain.Pageable;

public interface AccountService {

	Account createAccount();

	MyAccountResponse findMyAccounts(LoginMember loginMember, Pageable pageable);
}

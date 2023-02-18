package com.numble.banking.account.repository;

import com.numble.banking.account.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Boolean> existsByNumber(String accountNumber);
}

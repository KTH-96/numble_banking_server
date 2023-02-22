package com.numble.banking.account.repository;

import com.numble.banking.account.Account;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Boolean> existsByNumber(String accountNumber);

	@Query("select a from Account a join a.member where a.member.id = :member_id")
	Page<Account> findAccountById(@Param("member_id") Long id, Pageable pageable);

	Optional<Account> findByNumber(String accountNumber);
}

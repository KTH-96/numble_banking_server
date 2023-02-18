package com.numble.banking.member.repository;

import com.numble.banking.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Boolean> existsMembersByEmail(String email);
}

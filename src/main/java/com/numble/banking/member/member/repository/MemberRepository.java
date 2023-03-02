package com.numble.banking.member.member.repository;

import com.numble.banking.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsMembersByEmail(String email);

	Optional<Member> findByEmail(String email);

	boolean existsBySnsId(Long kakaoId);

	Optional<Member> findBySnsId(Long kakaoId);
}

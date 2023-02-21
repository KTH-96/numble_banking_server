package com.numble.banking.friend.repository;

import com.numble.banking.friend.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepository extends JpaRepository<Friend, Long> {

	@Query("select f from Friend f join f.member where f.member.id = :member_id")
	Page<Friend> findFriendById(@Param("member_id") Long id, Pageable pageable);
}

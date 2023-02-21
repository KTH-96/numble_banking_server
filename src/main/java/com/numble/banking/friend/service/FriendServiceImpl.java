package com.numble.banking.friend.service;

import static com.numble.banking.exception.ErrorCode.NOT_FIND_FRIEND;

import com.numble.banking.friend.Friend;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.exception.NotFindFriendException;
import com.numble.banking.friend.repository.FriendRepository;
import com.numble.banking.member.dto.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendServiceImpl implements FriendService{

	private final FriendRepository friendRepository;

	@Override
	public Page<FriendResponse> findMyFriend(LoginMember loginMember, Pageable pageable) {
		Page<Friend> friends = friendRepository.findFriendById(loginMember.getId(), pageable);
		if (friends.isEmpty()) {
			throw new NotFindFriendException(NOT_FIND_FRIEND);
		}
		return friends.map(FriendResponse::from);
	}
}

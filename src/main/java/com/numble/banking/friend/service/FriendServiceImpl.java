package com.numble.banking.friend.service;

import static com.numble.banking.exception.ErrorCode.NOT_FIND_FRIEND;
import static com.numble.banking.exception.ErrorCode.NOT_FIND_MEMBER;

import com.numble.banking.friend.Friend;
import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.dto.response.FriendSaveResponse;
import com.numble.banking.friend.exception.NotFindFriendException;
import com.numble.banking.friend.repository.FriendRepository;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.exception.NotFindMemberException;
import com.numble.banking.member.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
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
	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public List<FriendSaveResponse> saveFriends(Long loginMemberId, FriendListRequest friendsRequest) {
		Member member = memberRepository.findById(loginMemberId)
			.orElseThrow(() -> new NotFindMemberException(NOT_FIND_MEMBER));

		List<Friend> friends = Friend.save(friendsRequest.getFriendRequests(), member);
		changesMember(member, friends);

		friendRepository.saveAll(friends);
		return friends.stream().map(FriendSaveResponse::from)
			.collect(Collectors.toList());
	}

	private static void changesMember(Member member, List<Friend> friends) {
		for (Friend friend : friends) {
			friend.changeMember(member);
		}
	}

	@Override
	public Page<FriendResponse> findMyFriend(Long loginMemberId, Pageable pageable) {
		Page<Friend> friends = friendRepository.findFriendById(loginMemberId, pageable);
		if (friends.isEmpty()) {
			throw new NotFindFriendException(NOT_FIND_FRIEND);
		}
		return friends.map(FriendResponse::from);
	}
}

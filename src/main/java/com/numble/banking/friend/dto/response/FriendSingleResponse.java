package com.numble.banking.friend.dto.response;

import com.numble.banking.friend.Friend;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendSingleResponse {

	private final Long id;
	private final String accountNumber;
	private final String name;


	public static FriendSingleResponse from(Friend friend) {
		return new FriendSingleResponse(friend.getId(), friend.getAccountNumber(), friend.getName());
	}
}

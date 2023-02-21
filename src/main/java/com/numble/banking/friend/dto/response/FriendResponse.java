package com.numble.banking.friend.dto.response;

import com.numble.banking.friend.Friend;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendResponse {

	private final Long id;
	private final String accountNumber;
	private final String name;


	public static FriendResponse from(Friend friend) {
		return new FriendResponse(friend.getId(), friend.getAccountNumber(), friend.getName());
	}
}

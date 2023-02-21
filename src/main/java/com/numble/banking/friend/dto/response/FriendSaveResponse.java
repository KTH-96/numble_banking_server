package com.numble.banking.friend.dto.response;

import com.numble.banking.friend.Friend;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendSaveResponse {

	private final String name;

	public static FriendSaveResponse from(Friend friend) {
		return new FriendSaveResponse(friend.getName());
	}
}

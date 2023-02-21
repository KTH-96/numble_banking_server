package com.numble.banking.friend.dto.response;

import com.numble.banking.friend.Friend;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendListResponse {

	private final Page<FriendSingleResponse> friendSingleResponse;

	public static FriendListResponse from(Page<Friend> friends) {
		return new FriendListResponse(friends.map(FriendSingleResponse::from));
	}
}

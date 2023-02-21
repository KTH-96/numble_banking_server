package com.numble.banking.friend.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendListResponse {

	private final Page<FriendSingleResponse> friendSingleResponse;
}

package com.numble.banking.friend.dto.request;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FriendListRequest {

	@Valid
	@Size(min = 1)
	private List<FriendRequest> friendRequests;

	public static FriendListRequest from(FriendRequest... friendRequests) {
		return new FriendListRequest(List.of(friendRequests));
	}
}

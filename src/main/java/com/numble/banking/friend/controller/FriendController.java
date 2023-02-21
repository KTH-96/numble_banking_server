package com.numble.banking.friend.controller;

import com.numble.banking.common.utils.Login;
import com.numble.banking.friend.dto.response.FriendListResponse;
import com.numble.banking.friend.service.FriendService;
import com.numble.banking.member.dto.LoginMember;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	@GetMapping("/friend-list")
	public FriendListResponse friendList(
		@Login @Valid LoginMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return friendService.findMyFriend(loginMember, pageable);
	}
}

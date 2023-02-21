package com.numble.banking.friend.controller;

import com.numble.banking.common.utils.Login;
import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.dto.response.FriendSaveResponse;
import com.numble.banking.friend.service.FriendService;
import com.numble.banking.member.dto.LoginMember;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	@GetMapping("/friend-list")
	public Page<FriendResponse> friendList(
		@Login @Valid LoginMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return friendService.findMyFriend(loginMember, pageable);
	}

	@PostMapping("/friend-add")
	public List<FriendSaveResponse> addFriend(
		@Login @Valid LoginMember loginMember,
		@RequestBody @Valid FriendListRequest friendsRequest
		) {
		return friendService.saveFriends(loginMember, friendsRequest);
	}
}

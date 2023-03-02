package com.numble.banking.friend.controller;

import com.numble.banking.common.utils.LoginOauth;
import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.dto.response.FriendSaveResponse;
import com.numble.banking.friend.service.FriendService;
import com.numble.banking.member.oauth.LoginOauthMember;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	@GetMapping("/list")
	public Page<FriendResponse> friendList(
		@LoginOauth @Valid LoginOauthMember loginMember,
		@PageableDefault(size = 5) Pageable pageable
	) {
		return friendService.findMyFriend(loginMember.getId(), pageable);
	}

	@PostMapping("/add")
	public List<FriendSaveResponse> addFriend(
		@LoginOauth @Valid LoginOauthMember loginMember,
		@RequestBody @Valid FriendListRequest friendsRequest
		) {
		return friendService.saveFriends(loginMember.getId(), friendsRequest);
	}
}

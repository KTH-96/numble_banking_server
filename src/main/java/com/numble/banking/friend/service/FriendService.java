package com.numble.banking.friend.service;

import com.numble.banking.friend.dto.response.FriendListResponse;
import com.numble.banking.member.dto.LoginMember;
import org.springframework.data.domain.Pageable;

public interface FriendService {

	FriendListResponse findMyFriend(LoginMember loginMember, Pageable pageable);
}

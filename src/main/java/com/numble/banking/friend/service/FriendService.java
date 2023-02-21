package com.numble.banking.friend.service;

import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.member.dto.LoginMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendService {

	Page<FriendResponse> findMyFriend(LoginMember loginMember, Pageable pageable);
}

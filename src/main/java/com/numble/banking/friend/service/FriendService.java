package com.numble.banking.friend.service;

import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.dto.response.FriendSaveResponse;
import com.numble.banking.member.dto.LoginMember;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendService {

	Page<FriendResponse> findMyFriend(LoginMember loginMember, Pageable pageable);

	List<FriendSaveResponse> saveFriends(LoginMember loginMember, FriendListRequest friendsRequest);
}

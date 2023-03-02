package com.numble.banking.friend.service;

import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.dto.response.FriendSaveResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendService {

	Page<FriendResponse> findMyFriend(Long loginMemberId, Pageable pageable);

	List<FriendSaveResponse> saveFriends(Long loginMemberId, FriendListRequest friendsRequest);
}

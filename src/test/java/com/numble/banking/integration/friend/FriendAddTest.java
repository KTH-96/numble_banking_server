package com.numble.banking.integration.friend;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.friend.dto.request.FriendListRequest;
import com.numble.banking.friend.dto.request.FriendRequest;
import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.service.FriendService;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.exception.NotFindMemberException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DisplayName("친구추가 통합 테스트")
public class FriendAddTest extends InitIntegrationTest {

	@Autowired
	private FriendService friendService;

	@Test
	@DisplayName("내 친구추가 성공 테스트")
	void friend_add_success_test() {
	    //given 준비
		Member member = db.testMember1;
		FriendRequest friendRequest = FriendRequest.builder()
			.name("friend2")
			.accountNumber("friend2")
			.build();

		FriendListRequest friendListRequest = FriendListRequest.from(friendRequest);

		PageRequest pageRequest = PageRequest.of(0, 5);
		//when 실행
		friendService.saveFriends(member.getId(), friendListRequest);
		Page<FriendResponse> friends = friendService.findMyFriend(member.getId(), pageRequest);
		//then 검증
		assertThat(friends.getContent().size()).isEqualTo(2);
	}

	@Test
	@DisplayName("내 친구추가 실패 테스트")
	void friend_add_fail_test() {
		//given 준비
		Long id = Long.MIN_VALUE;
		FriendRequest friendRequest = FriendRequest.builder()
			.name("testMember3")
			.accountNumber("testAccount3")
			.build();

		FriendListRequest friendListRequest = FriendListRequest.from(friendRequest);
		//when 실행
		//then 검증
		assertThatThrownBy(() -> friendService.saveFriends(id, friendListRequest))
			.isInstanceOf(NotFindMemberException.class);

	}
}

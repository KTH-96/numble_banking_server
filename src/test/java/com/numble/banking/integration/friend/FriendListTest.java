package com.numble.banking.integration.friend;


import static org.assertj.core.api.Assertions.*;

import com.numble.banking.friend.dto.response.FriendResponse;
import com.numble.banking.friend.exception.NotFindFriendException;
import com.numble.banking.friend.service.FriendService;
import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DisplayName("내 친구찾기 통합 테스트")
@Slf4j
public class FriendListTest extends InitIntegrationTest {

	@Autowired
	private FriendService friendService;

	@Test
	@DisplayName("내 친구찾기 성공 테스트")
	void friend_find_success_test() {
	    //given 준비
		Member member = db.testMember1;
		PageRequest pageRequest = PageRequest.of(0, 5);
		//when 실행
		Page<FriendResponse> friendPage = friendService.findMyFriend(member.getId(), pageRequest);
		List<FriendResponse> friends = friendPage.getContent();
		//then 검증
		assertThat(friends.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("내 친구찾기 실패 테스트")
	void friend_find_fail_test() {
		//given 준비
		Long id = Long.MIN_VALUE;
		PageRequest pageRequest = PageRequest.of(0, 5);
		//when 실행
		//then 검증
		assertThatThrownBy(() -> friendService.findMyFriend(id, pageRequest))
			.isInstanceOf(NotFindFriendException.class);
	}
}

package com.numble.banking.integration.member;

import static org.assertj.core.api.Assertions.*;

import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("맴버 가입 통합 테스트")
public class MemberCreateTest extends InitIntegrationTest {

	@Autowired
	private MemberService memberService;

	@Test
	@DisplayName("맴버 생성 성공 테스트")
	void member_create_test() {
	    //given 준비
		Member member = new Member(3333L, "cMember", "cImage", "c@gmail.com");
	    //when 실행
		memberService.createMember(member);
		Member createMember = memberService.findBySnsId(member.getSnsId());
		//then 검증
		assertThat(createMember.getSnsId()).isEqualTo(3333L);
		assertThat(createMember.getName()).isEqualTo("cMember");
		assertThat(createMember.getProfileImage()).isEqualTo("cImage");
		assertThat(createMember.getEmail()).isEqualTo("c@gmail.com");
	}

	@Test
	@DisplayName("맴버가 이미 존재하는지 테스트")
	void member_existing_test() {
		//given 준비
		Member member = db.testMember1;
		//when 실행
		boolean existingBySnsId = memberService.isExistingBySnsId(member.getSnsId());
		//then 검증
		assertThat(existingBySnsId).isTrue();
	}
}

package com.numble.banking.integration.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.numble.banking.integration.InitIntegrationTest;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.exception.NotFindMemberException;
import com.numble.banking.member.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("맴버 찾기 통합 테스트")
public class MemberFindTest extends InitIntegrationTest {

	@Autowired
	private MemberService memberService;

	@Test
	@DisplayName("아이디로 맴버 찾기 성공 테스트")
	void member_find_success_test() {
	    //given 준비
		Member member = db.testMember1;
		//when 검증
		Member findMember = memberService.findById(member.getId());
		//then 검증
		assertThat(member.getId()).isEqualTo(findMember.getId());
		assertThat(member.getSnsId()).isEqualTo(findMember.getSnsId());
		assertThat(member.getName()).isEqualTo(findMember.getName());
		assertThat(member.getProfileImage()).isEqualTo(findMember.getProfileImage());
		assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
	}

	@Test
	@DisplayName("SNS 아이디로 맴버 찾기 성공 테스트")
	void member_find_SNS_success_test() {
		//given 준비
		Member member = db.testMember1;
		//when 검증
		Member findMember = memberService.findBySnsId(member.getSnsId());
		//then 검증
		assertThat(member.getId()).isEqualTo(findMember.getId());
		assertThat(member.getSnsId()).isEqualTo(findMember.getSnsId());
		assertThat(member.getName()).isEqualTo(findMember.getName());
		assertThat(member.getProfileImage()).isEqualTo(findMember.getProfileImage());
		assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
	}

	@Test
	@DisplayName("아이디로 맴버 찾기 실패 테스트")
	void member_find_fail_test() {
	    //given 준비
		Long id = Long.MIN_VALUE;

		//then 검증
		assertThatThrownBy(() -> memberService.findById(id))
			.isInstanceOf(NotFindMemberException.class);
	}

	@Test
	@DisplayName("SNS 아이디로 맴버 찾기 실패 테스트")
	void member_find_SNS_fail_test() {
		//given 준비
		Long id = Long.MIN_VALUE;

		//then 검증
		assertThatThrownBy(() -> memberService.findBySnsId(id))
			.isInstanceOf(NotFindMemberException.class);
	}
}

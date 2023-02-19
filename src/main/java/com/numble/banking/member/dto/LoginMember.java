package com.numble.banking.member.dto.request;


import com.numble.banking.member.Member;
import com.numble.banking.member.dto.response.MemberSignInResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginMember {

	@NotNull
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String accountNumber;

	public static LoginMember of(Member signInMember) {
		return new LoginMember(signInMember.getId(),
			signInMember.getName(),
			signInMember.findBasicAccountNumber());
	}
}

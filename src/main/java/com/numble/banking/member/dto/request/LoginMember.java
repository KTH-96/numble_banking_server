package com.numble.banking.member.dto.request;


import javax.validation.constraints.NotBlank;
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

	@NotBlank
	private Long memberId;

	@NotBlank
	private String name;

	@NotBlank
	private String accountNumber;
}

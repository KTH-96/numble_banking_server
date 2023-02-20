package com.numble.banking.member.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberSignUpRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 10, max = 15)
	private String password;

}

package com.numble.banking.member;

import static com.numble.banking.common.Constant.BASIC_ACCOUNT;

import com.numble.banking.account.Account;
import com.numble.banking.friend.Friend;
import com.numble.banking.member.dto.request.MemberSignUpRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "member_email", length = 20, unique = true)
	private String email;

	@Column(name = "member_name", length = 10)
	private String name;

	@Column(name = "member_password", length = 15)
	private String password;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Friend> friends = new ArrayList<>();

	@Builder
	public Member(String email, String name, String password, Account accounts) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.accounts.add(accounts);
	}

	public static Member createMember(MemberSignUpRequest signUpMember) {
		return Member.builder()
			.name(signUpMember.getName())
			.email(signUpMember.getEmail())
			.password(signUpMember.getPassword())
			.build();
	}

	public String findBasicAccountNumber() {
		return getAccounts().get(BASIC_ACCOUNT).getNumber();
	}
}

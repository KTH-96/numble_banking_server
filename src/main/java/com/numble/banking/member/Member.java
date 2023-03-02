package com.numble.banking.member;

import static com.numble.banking.common.Constant.BASIC_ACCOUNT;

import com.numble.banking.account.Account;
import com.numble.banking.friend.Friend;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(indexes = @Index(columnList = "sns_id, email", name = "sns_id_email_index"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "sns_id")
	private Long snsId;

	@Column(length = 10)
	private String name;

	@Column
	private String profileImage;

	@Column(length = 30, unique = true)
	private String email;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Friend> friends = new ArrayList<>();

	public Member(Long snsId, String name, String profileImage, String email) {
		this.snsId = snsId;
		this.name = name;
		this.profileImage = profileImage;
		this.email = email;
	}

	public String findBasicAccountNumber() {
		return getAccounts().get(BASIC_ACCOUNT).getNumber();
	}
}

package com.numble.banking.account;

import com.numble.banking.member.Member;
import java.text.DecimalFormat;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(name = "account_number", length = 20, unique = true)
	private String number;

	@Column(name = "account_money")
	private Long money;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Builder
	public Account(String number, Long money) {
		this.number = number;
		this.money = money;
	}

	public static Account createAccount(String accountNumber) {
		return Account.builder()
			.number(accountNumber)
			.money(0L)
			.build();
	}

	public void changeMember(Member member) {
		this.member = member;
		member.getAccounts().add(this);
	}

}

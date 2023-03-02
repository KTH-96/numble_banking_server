package com.numble.banking.account;

import static com.numble.banking.common.Constant.ZERO;
import static com.numble.banking.exception.ErrorCode.NOT_EMPTY_MONEY;
import static com.numble.banking.exception.ErrorCode.TOO_MUCH_MONEY;

import com.numble.banking.account.exception.NotEmptyMoneyException;
import com.numble.banking.account.exception.TooMuchMoneyException;
import com.numble.banking.member.Member;
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

	public void minusMoney(Long transferMoney) {
		if (this.money < transferMoney || this.money == ZERO) {
			throw new NotEmptyMoneyException(NOT_EMPTY_MONEY);
		}
		this.money -= transferMoney;
	}

	public void plusMoney(Long transferMoney) {
		try {
			this.money += transferMoney;
		} catch (StackOverflowError error) {
			throw new TooMuchMoneyException(TOO_MUCH_MONEY);
		}
	}
}

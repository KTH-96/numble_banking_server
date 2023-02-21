package com.numble.banking.friend;

import com.numble.banking.friend.dto.request.FriendRequest;
import com.numble.banking.member.Member;
import java.util.ArrayList;
import java.util.List;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "friend_account_number", length = 20, unique = true)
	private String accountNumber;

	@Column(name = "friend_name", length = 10)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Builder
	public Friend(String accountNumber, String name, Member member) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.member = member;
	}

	public static List<Friend> save(List<FriendRequest> friendRequests, Member member) {
		List<Friend> friends = new ArrayList<>();
		for (FriendRequest friendRequest : friendRequests) {
			friends.add(Friend.builder()
				.name(friendRequest.getName())
				.accountNumber(friendRequest.getAccountNumber())
				.member(member)
				.build());
		}
		return friends;
	}

	public void changeMember(Member member) {
		this.member = member;
		member.getFriends().add(this);
	}
}

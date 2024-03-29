package com.numble.banking.integration;

import com.numble.banking.account.Account;
import com.numble.banking.account.AccountNumber;
import com.numble.banking.account.repository.AccountRepository;
import com.numble.banking.friend.Friend;
import com.numble.banking.friend.dto.request.FriendRequest;
import com.numble.banking.friend.repository.FriendRepository;
import com.numble.banking.member.Member;
import com.numble.banking.member.member.repository.MemberRepository;
import com.numble.banking.member.oauth.service.JwtProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig implements InitializingBean {

	@PersistenceContext
	private EntityManager em;
	private List<String> tableNames;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private JwtProvider jwtProvider;

	public Member testMember1;
	public Member testMember2;
	public Account testAccount1;
	public Account testAccount2;
	public List<Friend> friends1;
	public List<Friend> friends2;
	public String member1JwtAccessToken;
	public String member2JwtAccessToken;
	public String member1RefreshToken;
	public String member1ExpiredRefreshToken;


	@Override
	public void afterPropertiesSet() throws Exception {
		em.unwrap(Session.class).doWork(this::getTableNames);
	}

	private void getTableNames(Connection connection) throws SQLException {
		this.tableNames = new ArrayList<>();

		ResultSet resultSet = connection.getMetaData()
			.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

		try (resultSet){
			while (resultSet.next()) {
				tableNames.add(resultSet.getString("table_name"));
			}
		}
	}

	public void clear() {
		em.unwrap(Session.class).doWork(this::clearDb);
	}

	private void clearDb(Connection connection) throws SQLException {
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");

			for (String tableName : tableNames) {
				statement.executeUpdate("TRUNCATE TABLE " + tableName);
			}

			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
		}
	}

	public void initDb() {
		initMemberData();
		initAccountData();
		initFriendData();
		initBasicData();
		initMemberAuthorizationData();
	}

	private void initMemberAuthorizationData() {
		Long member1Payload = this.testMember1.getId();
		Long member2Payload = this.testMember2.getId();

		Date now = new Date();
		Date accessTokenExpiredTime = new Date(now.getTime() + 1800000);

		this.member1JwtAccessToken = jwtProvider.createJwtToken(member1Payload, accessTokenExpiredTime);
		this.member2JwtAccessToken = jwtProvider.createJwtToken(member2Payload,accessTokenExpiredTime);
		this.member1RefreshToken = jwtProvider.createJwtToken(member1Payload, new Date(now.getTime()+8000000));
		this.member1ExpiredRefreshToken = jwtProvider.createJwtToken(member1Payload, new Date(now.getTime() - 1000));
	}

	private void initBasicData() {
		this.testMember1 = memberRepository.save(testMember1);
		this.testMember2 = memberRepository.save(testMember2);
		this.testAccount1 = accountRepository.save(testAccount1);
		this.testAccount2 = accountRepository.save(testAccount2);
		this.friends1 = friendRepository.saveAll(friends1);
		this.friends2 = friendRepository.saveAll(friends2);
	}

	private void initFriendData() {
		List<FriendRequest> friendRequests1 = new ArrayList<>();
		List<FriendRequest> friendRequests2 = new ArrayList<>();
		FriendRequest friendRequest1 = FriendRequest.builder()
			.accountNumber(testMember2.findBasicAccountNumber())
			.name(testMember2.getName())
			.build();
		friendRequests1.add(friendRequest1);
		this.friends1 = Friend.save(friendRequests1, testMember1);

		FriendRequest friendRequest2 = FriendRequest.builder()
			.accountNumber(testMember1.findBasicAccountNumber())
			.name(testMember1.getName())
			.build();
		friendRequests2.add(friendRequest2);
		this.friends2 = Friend.save(friendRequests2, testMember2);
	}

	private void initAccountData() {
		this.testAccount1 = Account.createAccount(AccountNumber.createAccountNumber());
		testAccount1.plusMoney(20000L);
		testAccount1.changeMember(testMember1);

		this.testAccount2 = Account.createAccount(AccountNumber.createAccountNumber());
		testAccount2.plusMoney(20000L);
		testAccount2.changeMember(testMember2);
	}

	private void initMemberData() {
		this.testMember1 = new Member(1111L, "멤버1",
			"https://png.pngtree.com/png-vector/20190411/ourmid/pngtree-vector-business-men-icon-png-image_925963.jpg",
			"testMember1@kakao.com");

		this.testMember2 = new Member(2222L, "멤버2",
			"https://png.pngtree.com/png-vector/20190411/ourmid/pngtree-vector-business-men-icon-png-image_925963.jpg",
			"testMember2@kakao.com");
	}
}

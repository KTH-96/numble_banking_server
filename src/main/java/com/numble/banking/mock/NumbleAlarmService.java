package com.numble.banking.mock;

public class NumbleAlarmService {


	public static void notify(Long myAccountMoney, String friendAccountNumber) {
		System.out.println(friendAccountNumber + " 계좌로 송금완료");
		System.out.println("잔액 : " + myAccountMoney);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

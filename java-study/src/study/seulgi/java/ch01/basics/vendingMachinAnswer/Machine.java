package study.seulgi.java.ch01.basics.vendingMachinAnswer;

import java.util.HashMap;
import java.util.Map;

public class Machine {
	private final String[] drinks = {"", "콜라", "사이다", "환타"};
	private final int[] prices = {0, 1000, 1200, 1300};

	// 어떤 음료를 얼마에 팔았는지 기록
	private Map<String, Integer> salseLog = new HashMap<>();
	private int currentMoney = 0;

	public void printList() {
		Map<String, Integer> listMap = new HashMap<>();
		for (int i = 1; i < drinks.length; i++) {
			System.out.printf("%d. %s (%d원)\n", i, drinks[i], prices[i]);
		}
	}

	public void insertMoney(int money) {
		currentMoney += money;
	}

	public int getCurrentMoney() {
		return currentMoney;
	}

	public String select(int choice) {
		// 내가 돈을 넣은 금액보다 음료수가 비싸면 안된다.
		if (currentMoney <prices[choice]) {
			return "돈이 부족합니다.";
		} else {
			currentMoney -= prices[choice];
			// 판매 기록에 기입
			salseLog.put(drinks[choice], prices[choice]);
		}
		return drinks[choice];
	}

	public Map<String, Integer> printSalesLog(){
		System.out.println("===== 매출 기록 =====");
		for (Map.Entry<String, Integer> entry : salseLog.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
		}
		return salseLog;
	}
	public void init(){
		// 자판기의 투입 금액을 0으로 초기화 하고 돈은 고객에게 반환.
		currentMoney = 0;
	}
	/*
	public int refund(){
		// 자판기의 투입 금액을 0으로 초기화 하고 돈은 고객에게 반환.
		// 방법1. 변수에 담아서 리턴하기

		int refund = currentMoney;
		currentMoney = 0;
		return refund;

	}*/

}

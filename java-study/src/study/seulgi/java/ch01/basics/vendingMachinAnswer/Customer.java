package study.seulgi.java.ch01.basics.vendingMachinAnswer;

import java.util.Scanner;

public class Customer {
	private int money; // 고객 하나는 머니를 가지고 있다.

	// 고객이 생성될 때 고객이 가지고 있는 돈을 세팅
	public Customer(){
		System.out.println("고객(당신)이 수중에 가지고 있는 돈을 입력해주세요");
		var scanner = new Scanner(System.in);
		money = scanner.nextInt();
		}

		// 돈 추가 입금
		public void addMoney(int money){
		this.money += this.money;
		}

		public int getMoney(){
			return
		}

		public int outputMoney(){
			// 내가 가진 내 돈에서 얼마를 넣을지 정하기
						Scanner sc = new Scanner(System.in);
			var outputMoney = sc.nextInt();

			if(outputMoney > money){
				System.out.println("돈이 부족합니다!!");
				return -99999;
			}
			return outputMoney;
		}
	public int pushDrinkButton(){
		System.out.println("원하는 음료수를 선택해주세요.");
		var scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	public void pushRefundButton(int amount){
		this.money += amount;
	}
}

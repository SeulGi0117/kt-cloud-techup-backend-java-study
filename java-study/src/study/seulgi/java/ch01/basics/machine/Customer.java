package study.seulgi.java.ch01.basics.machine;

import java.util.Scanner;

// Customer는 VO가 아니다. 불변성 포기할거면 얠 하는게 맞음
public class Customer {
	private Money wallet; // 고객 하나는 머니를 가지고 있다.

	// 고객이 생성될 때 고객이 가지고 있는 돈을 세팅
	public Customer() {
		System.out.println("고객(당신)이 수중에 가지고 있는 돈을 입력해주세요");
		var scanner = new Scanner(System.in);
		wallet = new Money(scanner.nextInt());
	}

	public Money getMoney() {
		return wallet;
	}

	public Money outputMoney() {
		// 내가 가진 내 돈에서 얼마를 넣을지 정하기
		Scanner sc = new Scanner(System.in);
		var outputMoney = new Money(sc.nextInt());

		// 기준보다 큰지 같은지 <= 이걸 머니에게 맡기면 자연스럽지 않을까?
		// 지갑한테 물어볼까?
		// 객사오- 객체란? 능동적으로 행동 할 수 있는 객체
		// if(wallet.나이거꺼대도됨?) 물어볼수있지 않을까? 이거 너무 유니크해보임
		// wallet.이돈이 너가가진 돈보다 작니?(이돈)

		if (wallet.isLessThan(outputMoney)) {
			System.out.println("돈이 부족합니다!!");
			return new Money(-99999);
		}
		wallet = wallet.subtract(outputMoney); // 돈 리턴할때 차감해줌
		return outputMoney();
	}

	public int pushDrinkButton() {
		System.out.println("원하는 음료수를 선택해주세요.");
		var scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	public void pushRefundButton(Money money) {
		// 환불해준 돈 지갑에 넣어줘야됨
		wallet = this.wallet.add(money);
	}
}

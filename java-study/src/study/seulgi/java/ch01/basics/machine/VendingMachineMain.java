package study.seulgi.java.ch01.basics.machine;

import java.util.Scanner;

public class VendingMachineMain {
	// VO: Value Object - 불변객체 - 비지니지스로직을 가져도 됨, 얘는 불변객체다
	// POJO 순수 자바로 이루어져 있고, 객체는 다르지만 그 가치가 같을때 - equals, hashcode - Drink, Money
	// DTO: Data Transfer Object - record - 비지니스로직을 가지면 x - getter만

	// TODO: 실행해서 테스트 할때 잔애기 -200으로 뜸. 이거 고쳐야함. 반드시 디버깅 하면서 작업.
	public static void main(String[] args) {
		var machine = new Machine();
		machine.printDrinks();

		var customer = new Customer();
		var admin = new Admin();

		var isFirst = true;

		while (true) {
			if (isFirst) {
				System.out.println("자판기에 투입할 금액을 입력해주세요.");
				var outputMoney = customer.outputMoney();

				if (outputMoney.getAmount() == -99999) {
					break;
				}
				machine.insertMoney(outputMoney);
			}

			// 손님이 돈 넣었으니 버튼을 눌러야됨
			var choice = customer.pushDrinkButton();
			var outputDrink = machine.select(choice);

			System.out.println("선택한 음료수  " + outputDrink);
			System.out.println("잔돈: " + machine.getCurrentMoney().getAmount() + "원");

			// 고객에게 돈을 더 넣을 건지 물어볼까? <= main 에서 물어보자
			System.out.println("금액을 더 넣으시겠어요? 그렇다면 0번을 눌러주세요.");
			Scanner sc = new Scanner(System.in);
			var moreMoney = sc.nextInt();

			if (moreMoney != 0){
				isFirst = false;}
			else {
				isFirst = true;
			}

			if (machine.getCurrentMoney().isZero()) {
				break;
			}
			// 자판기가 잔돈을 반환하고, 잔액이 0으로 초기화 되는것. 고객1이 구매를 종료하는 것
			// <= 이런거 할때 누구의 속성을 바꿔야 하는것인가? 를 생각하면 쉽다. currentMoney는 머신거라 머신에서 해야한다.
			// 요게 세터를 사용하지 말아야하는 이유이다. main에서 바꾸면 철호가 내이름 수정하는거랑 똑같음

			Scanner sc1 = new Scanner(System.in);
			System.out.println("잔돈을 반환하시겠습니까? 예(1)/아니오(0)");
			var refund = sc1.nextInt();

			if (refund == 1) {
				customer.pushRefundButton(machine.getCurrentMoney());
				machine.init();
				break;
			}
		}

		System.out.println("관리자로 로그인 하시곘어요? 예(1)/아니오(0)");
		Scanner sc2 = new Scanner(System.in);
		var adminLogin = sc2.nextInt();

		if (adminLogin == 1) {
			admin.printMenu();

			var menuChoice = sc2.nextInt();

			switch (menuChoice) {
				case 1, 3 -> System.out.println("[기능 구현 전입니다]");
				case 2 -> {
					admin.collect(machine);
					admin.printSalesHistory();
				}
			}
		}
	}
}
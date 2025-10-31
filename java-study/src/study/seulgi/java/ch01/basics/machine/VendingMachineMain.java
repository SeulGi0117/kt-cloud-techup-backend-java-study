package study.seulgi.java.ch01.basics.machine;

import java.util.Scanner;

public class VendingMachineMain {
	// VO Value Object 불변 객체 - 비지니스 로직을 가져도딤. 객체는 틀리지만 가치는 같은것.
	// 얘는 불변객체다
	// 순수 포조 자바로 이루어져 있고. 객체는 다르지만 그 가치가 같을떄 equals hashcode Drink Money
	// DTO Data Transfer Object - record 비지니스 로직을 가지면 안된다. getter만

	// TODO: 실행해서 테스트 할때 잔애기 -200으로 뜸. 이거 고쳐야함. 반드시 디버깅 하면서 작업.
	public static void main(String[] args) {
		var machine = new Machine();
		var customer = new Customer();
		var admin = new Admin();

		System.out.println("자판기 관리자로 로그인 하셨습니다.");
		machine.printDrinks();

		var isFirst = true;

		while (true) {
			if (isFirst) {
				System.out.println("자판기에 투입할 금액을 입력해주세요.");

				var outputMoney = customer.outputMoney();
				if (outputMoney.getAmount() == -99999)
					break;
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

			if (moreMoney != 0)
				continue;

			if (machine.getCurrentMoney().isZero) {
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
				isFirst = true;

			}
		}
		System.out.println("관리자로 로그인 하시곘어요? 예(1)/아니오(0)");
		Scanner sc2 =new Scanner(System.in);
		var adminLogin = sc2.nextInt();
		if(adminLogin == 1) {
			admin.printMenu();

			var menuChoice = sc2.nextInt();

			switch (menuChoice){
				case 1 -> System.out.println("[기능 구현 전입니다] 재고 추가 메뉴입니다.");
				case 2 -> {
					admin.collect(machine);
					// admin.printSalesHistory();
				}
				case 3 -> System.out.println("[기능 구현 전입니다] 재고 현황 출력 메뉴입니다.");
			}
		}
		machine.printSalesLog();
	}
}
/*
if 문으로 플래그 세워서 하는 방법

public class VendingMachineMain {
	public static void main(String[] args) {
		var machine = new Machine();

		System.out.println("===== 판매목록 =====");
		machine.printList();
		System.out.println("==================");

		var customer = new Customer();

		while(true){
			boolean isFirst = true;
			// System.out.println("자판기에 투입할 금액을 입력해주세요.");

			var outputMoney = customer.getMoney();
			if(outputMoney == -99999) break;

			machine.insertMoney(outputMoney);

			System.out.println("현재 투입 금액: "+machine.getCurrentMoney()+"원");

			// 손님이 돈 넣었으니 버튼을 눌러야됨
			var choice = customer.pushButton();
			var outputDrink = machine.select(choice);

			System.out.println("선택한 음료수  "+ outputDrink);
			System.out.println("잔돈: "+machine.getCurrentMoney()+"원");

			isFirst = false;

			// 고객에게 돈을 더 넣을 건지 물어볼까? <= main 에서 물어보자
			if(!isFirst){
				System.out.println("금액을 더 넣으시겠어요? 그렇다면 0번을 눌러주세요.");
				Scanner sc = new Scanner(System.in);
				var moreMoney = sc.nextInt();
				if(moreMoney != 0) continue;
			}
			if(machine.getCurrentMoney() == 0){
				break;
			}
		}
		machine.printSalesLog();
	}
}

*/

package study.seulgi.java.ch01.basics.vendingMachinAnswer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineMain {
	public static void main(String[] args) {
		var machine = new Machine();

		System.out.println("===== 판매목록 =====");
		machine.printList();
		System.out.println("==================");

		var customer = new Customer();

		boolean isFirst = true;
		while(true){
			if (isFirst){
				System.out.println("자판기에 투입할 금액을 입력해주세요.");

				var outputMoney = customer.outputMoney();
				if(outputMoney == -99999) break;
				machine.insertMoney(outputMoney);
			}

			System.out.println("현재 투입 금액: "+machine.getCurrentMoney()+"원");

			// 손님이 돈 넣었으니 버튼을 눌러야됨
			var choice = customer.pushButton();
			var outputDrink = machine.select(choice);

			System.out.println("선택한 음료수  "+ outputDrink);
			System.out.println("잔돈: "+machine.getCurrentMoney()+"원");

			// 고객에게 돈을 더 넣을 건지 물어볼까? <= main 에서 물어보자
			System.out.println("금액을 더 넣으시겠어요? 그렇다면 0번을 눌러주세요.");
			Scanner sc = new Scanner(System.in);
			var moreMoney = sc.nextInt();

			if(moreMoney != 0) continue;

			if(machine.getCurrentMoney() == 0){
				break;
			}
			// 자판기가 잔돈을 반환하고, 잔액이 0으로 초기화 되는것. 고객1이 구매를 종료하는 것
			// <= 이런거 할때 누구의 속성을 바꿔야 하는것인가? 를 생각하면 쉽다. currentMoney는 머신거라 머신에서 해야한다.
			// 요게 세터를 사용하지 말아야하는 이유이다. main에서 바꾸면 철호가 내이름 수정하는거랑 똑같음
			Scanner sc1 = new Scanner(System.in);
			System.out.println("잔돈을 반환하시겠습니까? 예(1)/아니오(0)");
			var refund = sc1.nextInt();

			if(refund ==1){
				customer.pushRefundButton(machine.getCurrentMoney());
				machine.init();

				customer.
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

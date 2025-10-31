package study.seulgi.java.ch01.basics.machine;

import java.util.List;
import java.util.Map;

// 자판기에
public class Machine {
	private final Inventory inventory;
	private final SalesRecoder salesRecoder;
	private Money currentMoney;
	private final PurchaseValidator purchaseValidator;

	public Machine() {
		this.inventory = new Inventory();
		this.salesRecoder = new SalesRecoder();
		this.currentMoney = new Money(0);
		this.purchaseValidator = new PurchaseValidator();
		initDrinks();
	}
	public void initDrinks() {
		inventory.addDrink(new Drink(1, "콜라", 1000));
		inventory.addDrink(new Drink(2, "사이다", 1200));
		inventory.addDrink(new Drink(3, "환타", 1300));
	}

	public Money getCurrentMoney() {
		return currentMoney;
	}

	public void printDrinks() {
		List<Drink> drinks = inventory.getDrinks();
		System.out.println("===== 판매목록 =====");
		for(Drink drink : drinks){
			System.out.println(drink);
		}
		System.out.println("==================");
	}

	public void insertMoney(Money money) {
		currentMoney = currentMoney.add(money);
		purchaseValidator.add(currentMoney); // 동기화 직접 맞추어 줘야 하기 때문에
		System.out.println("현재 투입 금액: " + currentMoney.getAmount() + "원");
	}

	public Drink select(int drinkId) {
		// 음료의 가격을 가져와서 현재 내 잔액보다 작거나 같은지 확인 ()내가 돈을 넣은 금액보다 음료수가 비싸면 안된다.)
		// 근데 이걸 자판기가 가져도 되는거 맞음? SRP 위반아님? => 검증 클래스 따로만들자

		// TODO: 여기 null 처리 해야함. Optional 사용해서 해보기.
		var drink = inventory.getDrink(drinkId);

		if(purchaseValidator.canNotPurchase(drink.getPrice())){
			System.out.println("잔액이 부족합니다.");
			return null;
		}
		currentMoney = currentMoney.subtract(drink.getPrice());
		salesRecoder.record(drink);

		return drink;
	}

	public Map<String, Integer> printSalesLog() {
		System.out.println("===== 매출 기록 =====");
		for (Map.Entry<String, Integer> entry : salseLog.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
		}
		return salseLog;
	}

	public void init() {
		// 자판기의 투입 금액을 0으로 초기화 하고 돈은 고객에게 반환.
		currentMoney = new Money(0);
	}


	public SalesRecoder getSalesRecoder(){
		return SalesRecoder;
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

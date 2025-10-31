package study.seulgi.java.ch01.basics.machine;

public class PurchaseValidator {
	private Money insertedMoney;

	public PurchaseValidator(){
		this.insertedMoney = new Money(0);
	}

	public boolean canPurchase(Money drinkPrice){
		// 항상 머신의 잔액 기준으로 동작한다.
		// 나 이거 검증해야하는데 머니야 이거 가격 맞음? 물어보기
		return insertedMoney.isGreaterTeanOrEqual(drinkPrice);
	}

	public boolean canNotPurchase(Money drinkPrice){
		// return insertedMoney.isLessThan(drinkPrice);
		return !canPurchase(drinkPrice);

	}

	public void add(Money amount){
		insertedMoney = insertedMoney.add(amount);
	}

}

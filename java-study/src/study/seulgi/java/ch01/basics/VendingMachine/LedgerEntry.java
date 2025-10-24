package study.seulgi.java.ch01.basics.VendingMachine;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

/**
 * 장부의 하루치 기록을 담는 클래스.
 * 날짜, 총 매출, 그리고 상품별 판매 기록을 저장합니다.
 */
public class LedgerEntry {
	private final LocalDate date;
	private final int amount;
	private final Map<String, Integer> soldProducts;

	public LedgerEntry(LocalDate date, int amount, Map<String, Integer> soldProducts) {
		this.date = date;
		this.amount = amount;
		// 외부에서 수정할 수 없도록 새로운 Map으로 복사하여 저장
		this.soldProducts = Map.copyOf(soldProducts);
	}

	public LocalDate getDate() { return date; }
	public int getAmount() { return amount; }

	/**
	 * 상품별 판매 기록을 반환한다.
	 * 상품별 판매 기록을 반환한다.
	 * 상품 이름과 판매 수량이 담긴 Map을 리턴함.
	 */
	public Map<String, Integer> getSoldProducts() {
		return soldProducts;
	}
}
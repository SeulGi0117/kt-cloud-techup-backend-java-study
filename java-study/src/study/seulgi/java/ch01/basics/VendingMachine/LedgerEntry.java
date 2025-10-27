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
	// total Sales Amount, 하나 팔때마다 하나 추가
	// 2025-10-27
	// 포카리 1개 1200원에 팜
	// 바운더리 컨테스트가 불명확하가. 레저와 레저엔트리와.
	// 레저가 장부를 뜻한느 거라면 이 엔트리가 굳이 필요했을까?
	// 강사님이었다면 엔트리를 없애고 하나로 합쳤을것이다.
	// soldProducts.put(date.toString(), amount)

	// public void record(String name, int amount, Map<String, Integer> soldProducts){
	// 	soldProducts.put(name, amount);
	// }

	public LocalDate getDate() {
		return date;
	}

	public int getAmount() {
		return amount;
	}

	/**
	 * 상품별 판매 기록을 반환한다.
	 * 상품별 판매 기록을 반환한다.
	 * 상품 이름과 판매 수량이 담긴 Map을 리턴함.
	 */
	public Map<String, Integer> getSoldProducts() {
		return soldProducts;
	}
}
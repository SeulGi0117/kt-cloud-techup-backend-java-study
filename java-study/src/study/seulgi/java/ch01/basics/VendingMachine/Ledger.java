package study.seulgi.java.ch01.basics.VendingMachine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// 자판기의 전체 판매 기록(장부)을 관리하는 클래스.
public class Ledger {
	private final List<LedgerEntry> entries = new ArrayList<>();

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

	// 하루 동안의 판매 기록을 장부에 추가합니다. 날짜, 매출액, 상품별 판매기록
	public void record(LocalDate date, int amount, Map<String, Integer> soldProducts) {
		entries.add(new LedgerEntry(date, amount, soldProducts));
	}

	// 기록된 모든 장부 내용을 반환
	public List<LedgerEntry> getAll() {
		return Collections.unmodifiableList(entries);
	}
}
package study.seulgi.java.ch01.basics.VendingMachine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// 자판기의 전체 판매 기록(장부)을 관리하는 클래스.
public class Ledger {
	private final List<LedgerEntry> entries = new ArrayList<>();

	// 하루 동안의 판매 기록을 장부에 추가합니다. 날짜, 매출액, 상품별 판매기록
	public void record(LocalDate date, int amount, Map<String, Integer> soldProducts) {
		entries.add(new LedgerEntry(date, amount, soldProducts));
	}
	// 기록된 모든 장부 내용을 반환
	public List<LedgerEntry> getAll() {
		return Collections.unmodifiableList(entries);
	}
}
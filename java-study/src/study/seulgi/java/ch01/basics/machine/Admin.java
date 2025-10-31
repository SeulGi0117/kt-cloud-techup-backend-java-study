package study.seulgi.java.ch01.basics.machine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 관리자는 자판기 100개도 관리할수있다. 그래서 자판기를 접근가능하게 인자로 주면 어떨까?

public class Admin {
	//TODO:  얘도 객체로 만들수 있지 않을까?
	private final Map<LocalDate, List<Drink>> salesHistory;

	public Admin() {
		this.salesHistory = new HashMap<>();
	}
	public void printMenu(){
		System.out.println("===== 관리자 메뉴 =====");
		System.out.println("1. 재고추가");
		System.out.println("2. 매출 기록 출력"); // 오늘 날짜의 sales 데이터를쭉 가져오고 싶다.
		System.out.println("3. 재고 현황 출력");
		System.out.println("====================");
	}

	// 관리한다
	public void collect(Machine machine){
		// machine 에 있는 세일즈 레코더를 가져온다
		salesHistory.put(LocalDate.now(),
			machine.getSalesRecoder().getSalesLog()
		);
	}

	// TODO: 총 매출, 총 집계 넣기
	public void printSalesHistory(){
		System.out.println("===== 매출 기록 =====");
		for(Map.Entry<LocalDate, List<Drink>> entry : salesHistory.entrySet()){
			System.out.println("날짜: " +entry.getKey());
			for(Drink drink: entry.getValue()){
				System.out.printf("%s: %d원\n", drink.getName(), drink.getPrice().getAmount());
			}
			System.out.println("총 매출: " +entry.getValue().stream().mapToInt(drink -> drink.getPrice().getAmount()).sum() + "원");
			System.out.println("===================");
		}
	}

}

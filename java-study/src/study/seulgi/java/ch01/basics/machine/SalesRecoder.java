package study.seulgi.java.ch01.basics.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesRecoder {
	// 음료명, 금액이 들어왔는데 이게 => Drink 로 바뀌었다.
	private final List<Drink> salseLogs;

	public SalesRecoder() {
		this.salseLogs = new ArrayList<>();
	}

	public void record(Drink drink){
		salseLogs.add(drink);
	}

	public List<Drink> getSalesLog(){
		return Collections.unmodifiableList(salseLogs);
	}

	public void init(){
		salseLogs.clear();
	}
	public void printLog(){
		System.out.println("===== 매출기록 =====");
		for(Drink drink: salseLogs){
			System.out.println("%s: %d원\n, drink.getName(), drink.getPrice()");
		}
		System.out.println("==================");
	}
}

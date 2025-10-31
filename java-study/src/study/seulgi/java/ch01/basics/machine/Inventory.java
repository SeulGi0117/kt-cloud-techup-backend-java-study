package study.seulgi.java.ch01.basics.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 재고. 자판기에 진열되어 있는것처럼
public class Inventory {
	private final Map<Integer, Drink> drinks;
	// 1,콜라
	// 2, 사이다
	// 이런식으로 넣어야될것같다

	// 인벤토리에 콜라 넣어주는 작업
	public Inventory() {
		this.drinks = new HashMap<>();
		// 외부에서 안받고 생성해주는걸로
	}

	public void addDrink(Drink drink){
		drinks.put(drink.getId(), drink);
	}
	public Drink getDrink(int id){
		return drinks.get(id);
	}

	public List<Drink> getDrinks(){
		return List.copyOf(drinks.values());
	}

	public Drink getPrice(){
		return drinks.get(getPrice());
	}
}
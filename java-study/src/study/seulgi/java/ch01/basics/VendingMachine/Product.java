package study.seulgi.java.ch01.basics.VendingMachine;

public class Product {
	private final String name;
	private final	int price;

	public Product(String name, int price){
		if (price <0) throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
		this.name = name;
		this.price = price;
	}
	public String getName(){
		return name;
	}
	public int getPrice(){
		return price;
	}

}

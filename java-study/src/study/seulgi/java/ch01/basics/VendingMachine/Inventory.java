package study.seulgi.java.ch01.basics.VendingMachine;

public class Inventory {
	private final String code;
	private final Product product;
	private int quantity;

	public Inventory(String code, Product product, int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("수량은 0 이상이어야 합니다.");
		this.code = code;
		this.product = product;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	} // 개수

	public boolean hasStock() {
		return quantity > 0;
	}

	public void decreaseOne() {
		if (quantity <= 0)
			throw new IllegalStateException("재고가 부족합니다.");
		quantity--;
	}

	public void add(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("추가 수량은 0 이상이어야 합니다.");
		quantity += amount;
	}
}

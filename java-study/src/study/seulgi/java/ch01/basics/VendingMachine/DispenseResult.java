package study.seulgi.java.ch01.basics.VendingMachine;

public class DispenseResult {
	private final boolean isOk;
	private final String message;
	private final String productName;
	private final int change;

	// 자판기에서 음료 뽑으면 결과 담기
	private DispenseResult(boolean isOk, String message, String productName, int change) {
		this.isOk = isOk;        // 구매성공
		this.message = message;  // 메세지, 성공, 잔액부족
		this.productName = productName;  // 음료수 이름
		this.change = change;    // 남은 잔액
	}

	public static DispenseResult success(String productName, int change) {
		return new DispenseResult(true, "구매 성공", productName, change);
	}

	public static DispenseResult fail(String reason) {
		return new DispenseResult(false, reason, null, 0);
	}

	public boolean isOk() {
		return isOk;
	}

	public String getMessage() {
		return message;
	}

	public String getProductName() {
		return productName;
	}

	public int getChange() {
		return change;
	}
}

package study.seulgi.java.ch01.basics.VendingMachine;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class VendingMachine {
	private final Map<String, Inventory> slots = new LinkedHashMap<>();
	private final Ledger ledger = new Ledger();
	private boolean poweredOn = true;

	private int inserted;
	private int salesToday; // 일일 총 매출액
	private final Map<String, Integer> dailySoldProducts = new HashMap<>(); // 상품별 판매 개수

	private static final ZoneId KST = ZoneId.of("Asia/Seoul");

	public VendingMachine() {
	}

	public void addNewProduct(String code, String name, int price, int quantity) {
		Product product = new Product(name, price);
		Inventory inventory = new Inventory(code, product, quantity);
		slots.put(code, inventory);
	}

	public boolean addStock(String code, int qty) {
		Inventory slot = slots.get(code);
		if (slot == null)
			return false;
		slot.add(qty);
		return true;
	}

	public Inventory getInventory(String code) {
		return slots.get(code);
	}

	public Collection<Inventory> getInventorySlots() {
		return Collections.unmodifiableCollection(slots.values());
	}

	public boolean isPoweredOn() {
		return poweredOn;
	}

	public void insert(int amount) {
		ensureOn();
		if (amount <= 0)
			throw new IllegalArgumentException("투입 금액은 1원 이상이어야 합니다.");
		inserted += amount;
	}

	public String menu(boolean showBalance) {
		StringBuilder sb = new StringBuilder();
		// 상품 목록
		if (slots.isEmpty()) {
			sb.append("현재 판매중인 상품이 없습니다.\n");
		} else {
			slots.values().stream()
				.sorted(Comparator.comparing(inv -> Integer.parseInt(inv.getCode())))
				.forEach(s -> sb.append(String.format("#%s [%s %,d원 %d개]\n",
					s.getCode(), s.getProduct().getName(), s.getProduct().getPrice(), s.getQuantity())));
		}

		// 잔액 표시 (필요할 때만)
		if (showBalance) {
			sb.append(String.format("투입 금액: %,d원\n", inserted));
		}
		return sb.toString();
	}

	public DispenseResult select(String code) {
		ensureOn();
		Inventory slot = slots.get(code);
		if (slot == null)
			return DispenseResult.fail("존재하지 않는 코드입니다.");
		if (!slot.hasStock())
			return DispenseResult.fail("재고가 부족합니다!");

		Product product = slot.getProduct();
		int price = product.getPrice();
		if (inserted < price) {
			return DispenseResult.fail(String.format("잔액 부족: %,d원이 더 필요합니다.", price - inserted));
		}

		slot.decreaseOne();
		inserted -= price;

		// 판매 기록 업데이트
		salesToday += price;
		dailySoldProducts.merge(product.getName(), 1, Integer::sum);

		return DispenseResult.success(product.getName(), inserted);
	}

	public int refund() {
		ensureOn();
		int out = inserted;
		inserted = 0;
		return out;
	}

	public void togglePower() {
		poweredOn = !poweredOn;
	}

	// 일일 매출을 수금하고 장부에 상세 내역을 기록한다.
	// 기록 후 일일 데이터는 초기화 해야한다.
	public int collectSalesAndRecord() {
		LocalDate today = LocalDate.now(KST);
		int collected = salesToday;

		// 판매된 상품이 있을 때만 장부에 기록
		if (collected > 0) {
			ledger.record(today, collected, dailySoldProducts);
		}

		// 일일 데이터 초기화
		salesToday = 0;
		dailySoldProducts.clear();

		return collected;
	}

	// 장부 전체 서식에 맞춰서 출력하기
	public String printLedgerAll() {
		StringBuilder sb = new StringBuilder("-- [전체 장부] --\n");
		List<LedgerEntry> entries = ledger.getAll();

		if (entries.isEmpty()) {
			return "장부 기록이 없습니다.";
		}

		int grandTotal = 0;
		for (LedgerEntry e : entries) {
			sb.append(String.format("\n[날짜: %s]\n", e.getDate()));
			sb.append(String.format("  총 매출: %,d원\n", e.getAmount()));
			if (!e.getSoldProducts().isEmpty()) {
				sb.append("  - 판매 내역 -\n");
				e.getSoldProducts().entrySet().stream()
					.forEach(entry -> sb.append(String.format("    > %s: %d개\n", entry.getKey(), entry.getValue())));
			}
			grandTotal += e.getAmount();
		}
		sb.append(String.format("\n------------------\n누적 총 매출: %,d원\n", grandTotal));
		return sb.toString();
	}

	private void ensureOn() {
		if (!poweredOn)
			throw new IllegalStateException("자판기가 꺼져 있습니다.");
	}

	public int getInserted() {
		return inserted;
	}

	public int getSalesToday() {
		return salesToday;
	}
}
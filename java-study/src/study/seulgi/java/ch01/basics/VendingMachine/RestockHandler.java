package study.seulgi.java.ch01.basics.VendingMachine;

import java.util.Scanner;

public class RestockHandler {
	private final VendingMachine vm;
	private final Scanner scanner;
	private final InputValidator validator;
	private final DisplayManager display;

	public RestockHandler(VendingMachine vm, Scanner scanner, InputValidator validator, DisplayManager display) {
		this.vm = vm;
		this.scanner = scanner;
		this.validator = validator;
		this.display = display;
	}

	// 재고 관리 메인 루프
	public void handle() {
		while (true) {
			display.showInventoryStatus(vm.getInventorySlots());
			System.out.print("재고를 관리할 슬롯 번호(숫자)를 입력하세요 (뒤로가기: exit)> ");
			String slotInput = scanner.nextLine().trim();

			if (validator.isExitCommand(slotInput)) {
				break;
			}

			if (!validator.isInteger(slotInput)) {
				display.showError("슬롯 번호는 정수 숫자로 입력해야 합니다.");
				continue;
			}

			Inventory inventory = vm.getInventory(slotInput);

			if (inventory != null) {
				handleExistingProduct(slotInput, inventory);
			} else {
				handleNewProduct(slotInput);
			}
		}
	}

	// 기존 상품 재고 추가
	private void handleExistingProduct(String slotCode, Inventory inventory) {
		System.out.printf("'%s'는 현재 %d개 있습니다. 몇 개를 추가하시겠습니까? (취소: 0)> ",
			inventory.getProduct().getName(), inventory.getQuantity());

		String qtyInput = scanner.nextLine().trim();

		if (!validator.isInteger(qtyInput)) {
			display.showError("수량은 숫자로 입력해야 합니다.");
			return;
		}

		int qtyToAdd = Integer.parseInt(qtyInput);

		if (qtyToAdd > 0) {
			vm.addStock(slotCode, qtyToAdd);
			System.out.printf("완료: %s 재고가 %d개 되었습니다.%n",
				inventory.getProduct().getName(),
				vm.getInventory(slotCode).getQuantity());
		} else {
			System.out.println("취소되었습니다.");
		}
	}

	// 새 상품 추가
	private void handleNewProduct(String slotCode) {
		System.out.printf("%s번 슬롯에 새 상품을 추가합니다.%n", slotCode);

		// 상품명 입력
		System.out.print("음료 이름: ");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) {
			System.out.println("이름이 비어있어 취소되었습니다.");
			return;
		}

		int price = readPositiveInteger("음료 가격: ", "가격은 0 이상이어야 합니다.");
		if (price < 0)
			return; // 취소됨
		int quantity = readPositiveInteger("음료 개수 (취소: 0): ", "개수는 0 이상이어야 합니다.");
		if (quantity < 0)
			return; // 취소됨

		if (quantity > 0) {
			vm.addNewProduct(slotCode, name, price, quantity);
			System.out.println("완료: 새 상품이 추가되었습니다.");
		} else {
			System.out.println("취소되었습니다.");
		}
	}

	private int readPositiveInteger(String prompt, String errorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();

			if (!validator.isInteger(input)) {
				display.showError("숫자로 입력해야 합니다.");
				continue;
			}

			int value = Integer.parseInt(input);
			if (value >= 0) {
				return value;
			}

			display.showError(errorMessage);
		}
	}
}
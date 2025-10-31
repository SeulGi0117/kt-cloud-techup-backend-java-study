package study.seulgi.java.ch01.basics.VendingMachine;

import java.util.Scanner;

// 사용자 모드 관리
public class UserModeController {
	private final VendingMachine vm;
	private final Scanner scanner;
	private final InputValidator validator;
	private final DisplayManager display;

	public UserModeController(VendingMachine vm, Scanner scanner, InputValidator validator, DisplayManager display) {
		this.vm = vm;
		this.scanner = scanner;
		this.validator = validator;
		this.display = display;
	}

	public ModeTransition execute() {
		try {
			if (vm.getInserted() == 0) {
				return handleMoneyInsertionPhase();
			} else {
				return handleProductSelectionPhase();
			}
		} catch (Exception e) {
			display.showError(e.getMessage());
			return ModeTransition.STAY_USER;
		}
	}

	private ModeTransition handleMoneyInsertionPhase() {
		display.showInsertedAmount(vm.getInserted());
		display.showCurrentDate();
		display.showMenu(vm, false);
		display.promptForMoney();

		while (true) {
			String input = scanner.nextLine().trim();

			// 종료 명령
			if (validator.isExitCommand(input)) {
				return ModeTransition.EXIT_PROGRAM;
			}

			// 관리자 모드 전환 (돈이 없을 때만)
			if (validator.isAdminModeCommand(input)) {
				if (vm.getInserted() == 0) {
					return ModeTransition.TO_ADMIN;
				} else {
					display.showError("잔액이 있는 상태에서는 관리자 모드로 전환할 수 없습니다.");
					display.promptForMoney();
					continue;
				}
			}

			// 엔터 입력 (돈 투입 완료)
			if (input.isEmpty()) {
				if (vm.getInserted() > 0) {
					return ModeTransition.STAY_USER; // 음료 선택 단계로
				} else {
					display.promptForMoney();
					continue;
				}
			}

			// 돈 투입 처리
			if (validator.isInteger(input)) {
				int amount = Integer.parseInt(input);
				if (validator.isValidBillAmount(amount)) {
					vm.insert(amount);
					display.showMenu(vm, false);
					display.showMoneyAdded(vm.getInserted(), amount);
					display.promptForMoreMoney();
				} else {
					display.showError("1,000원 단위의 지폐만 투입 가능합니다.");
					display.promptForMoney();
				}
			} else {
				display.showError("잘못된 입력입니다. 금액(숫자)을 입력하세요.");
				display.promptForMoney();
			}
		}
	}

	// 음료 선택 단계
	private ModeTransition handleProductSelectionPhase() {
		display.showInsertedAmount(vm.getInserted());
		display.showCurrentDate();
		display.showMenu(vm, true);
		display.promptForProductSelection();

		String input = scanner.nextLine().trim();

		if (validator.isExitCommand(input)) {
			return ModeTransition.EXIT_PROGRAM;
		}

		// 음료 코드 입력
		if (validator.isProductCode(input)) {
			String code = input.substring(1);
			DispenseResult result = vm.select(code);

			if (result.isOk()) {
				display.showPurchaseSuccess(result.getProductName(), result.getChange());
				vm.refund(); // 잔돈 반환
			} else {
				display.showPurchaseFailure(result.getMessage());
			}
			return ModeTransition.STAY_USER;
		}

		// 숫자 입력 (환불 또는 추가 투입)
		if (validator.isInteger(input)) {
			int value = Integer.parseInt(input);

			// 환불
			if (validator.isRefundCommand(input)) {
				int refundAmount = vm.refund();
				display.showRefund(refundAmount);
				return ModeTransition.STAY_USER;
			}

			// 추가 돈 투입
			if (value > 0) {
				if (validator.isValidBillAmount(value)) {
					vm.insert(value);
					return ModeTransition.STAY_USER;
				} else {
					display.showError("1,000원 단위의 지폐만 투입 가능합니다.");
					return ModeTransition.STAY_USER;
				}
			} else {
				display.showError("잘못된 입력입니다.");
				return ModeTransition.STAY_USER;
			}
		}

		display.showError("잘못된 입력입니다. 음료 코드는 #1 형식으로 입력하세요.");
		return ModeTransition.STAY_USER;
	}

	public enum ModeTransition {
		STAY_USER,      // 사용자 모드 유지
		TO_ADMIN,       // 관리자 모드로 전환
		EXIT_PROGRAM    // 프로그램 종료
	}
}
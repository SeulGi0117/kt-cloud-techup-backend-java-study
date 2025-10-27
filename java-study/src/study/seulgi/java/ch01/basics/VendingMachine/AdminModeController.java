package study.seulgi.java.ch01.basics.VendingMachine;

import java.util.Scanner;

// 관리자 모드
public class AdminModeController {
	private final AdminConsol admin;
	private final VendingMachine vm;
	private final Scanner scanner;
	private final InputValidator validator;
	private final DisplayManager display;
	private final RestockHandler restockHandler;

	public AdminModeController(AdminConsol admin, VendingMachine vm, Scanner scanner,
		InputValidator validator, DisplayManager display) {
		this.admin = admin;
		this.vm = vm;
		this.scanner = scanner;
		this.validator = validator;
		this.display = display;
		this.restockHandler = new RestockHandler(vm, scanner, validator, display);
	}

	public boolean execute() {
		try {
			display.promptAdminCommand();
			String command = scanner.nextLine().trim().toLowerCase();

			switch (command) {
				case "exit":
					return true; // 사용자 모드로 전환

				case "report":
					handleReport();
					break;

				case "collect":
					handleCollect();
					break;

				case "power":
					handlePower();
					break;

				case "restock":
					restockHandler.handle();
					break;

				default:
					display.showError("잘못 입력했습니다. 이 명령어들만 입력가능합니다. "
						+ "[restock(재고추가), report(장부보고), collect(매출 회수), "
						+ "power(자판기 전원), exit(관리자모드 나가기)]");
					break;
			}

			return false; // 관리자 모드 유지함

		} catch (Exception e) {
			display.showError(e.getMessage());
			return false;
		}
	}

	// 장부 보고
	private void handleReport() {
		String report = admin.reportAll();
		display.showLedgerReport(report);
	}

	// 매출 회수
	private void handleCollect() {
		int collected = admin.collect();
		display.showCollectionComplete(collected);
	}

	// 전원 관린
	private void handlePower() {
		admin.powerToggle();
		display.showPowerStatus(vm.isPoweredOn());
	}
}
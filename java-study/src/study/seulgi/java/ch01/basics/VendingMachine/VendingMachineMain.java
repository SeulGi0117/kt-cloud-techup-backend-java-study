package study.seulgi.java.ch01.basics.VendingMachine;

import java.util.Scanner;

import study.seulgi.java.ch01.basics.VendingMachine.UserModeController.ModeTransition;

public class VendingMachineMain {

	enum Mode {USER, ADMIN}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		VendingMachine vm = new VendingMachine();
		AdminConsol admin = new AdminConsol(vm);
		InputValidator validator = new InputValidator();
		DisplayManager display = new DisplayManager();
		UserModeController userController = new UserModeController(vm, scanner, validator, display);
		AdminModeController adminController = new AdminModeController(admin, vm, scanner, validator, display);

		Mode currentMode = Mode.USER;

		// 시작 메시지 출력
		display.showStartMessage();

		// 메인 루프
		while (true) {
			try {
				if (currentMode == Mode.USER) {
					currentMode = handleUserMode(userController, display);
					if (currentMode == null) {
						break; // 자판시 시스템 조욜하기
					}
				} else {
					currentMode = handleAdminMode(adminController, display);
				}
			} catch (Exception e) {
				display.showError(e.getMessage());
			}
		}

		scanner.close();
	}

	private static Mode handleUserMode(UserModeController controller, DisplayManager display) {
		ModeTransition transition = controller.execute();

		switch (transition) {
			case STAY_USER:
				return Mode.USER;
			case TO_ADMIN:
				display.showAdminModeEnter();
				return Mode.ADMIN;
			case EXIT_PROGRAM:
				display.showExitMessage();
				return null;
			default:
				return Mode.USER;
		}
	}

	// 관리자 모드 관련
	private static Mode handleAdminMode(AdminModeController controller, DisplayManager display) {
		boolean shouldExitAdminMode = controller.execute();

		if (shouldExitAdminMode) {
			display.showUserModeEnter();
			return Mode.USER;
		}

		return Mode.ADMIN;
	}
}
package study.seulgi.java.ch01.basics.VendingMachine;

// 사용자 입력 유효성 검증. 지폐만 받기

public class InputValidator {

	public boolean isInteger(String input) {
		if (input == null || input.isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// 지폐 단위(1000원 단위) 검증
	public boolean isValidBillAmount(int amount) {
		return amount >= 1000 && amount % 1000 == 0;
	}

	// 음료 코드 형식 검증 (#1, #2 등)
	public boolean isProductCode(String input) {
		return input != null && input.startsWith("#") && input.length() > 1;
	}

	// 종료 명령어
	public boolean isExitCommand(String input) {
		return input != null && input.equalsIgnoreCase("exit");
	}

	// 관리자 모드 전환 명령어 확인
	public boolean isAdminModeCommand(String input) {
		return "-99".equals(input);
	}

	// 환불 명령어
	public boolean isRefundCommand(String input) {
		return "-1".equals(input);
	}
}
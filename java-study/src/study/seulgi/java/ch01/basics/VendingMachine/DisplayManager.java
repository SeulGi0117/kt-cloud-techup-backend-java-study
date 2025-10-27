package study.seulgi.java.ch01.basics.VendingMachine;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;

public class DisplayManager {
	private static final ZoneId KST = ZoneId.of("Asia/Seoul");

	// 프로그램 시작 메시지
	public void showStartMessage() {
		System.out.println("=====START====");
		System.out.println("[지폐전용자판기] 최소투입금액은 1,000원입니다. 1,000원 단위로만 투입 가능합니다.");
		System.out.println("사용자 모드: 금액(정수) 투입, 코드(#1/#2/..), -1(잔돈), -99(관리자모드)");
	}

	// 현재 날짜 출력
	public void showCurrentDate() {
		String dateStr = LocalDate.now(KST).format(DateTimeFormatter.ofPattern("M월 d일 E요일"));
		System.out.printf("== %s ==%n", dateStr);
	}

	// 투입 금액 표시
	public void showInsertedAmount(int amount) {
		System.out.printf("투입 금액: [%,d]원%n", amount);
	}

	// 메뉴 출력
	public void showMenu(VendingMachine vm, boolean showBalance) {
		System.out.println(vm.menu(showBalance));
	}

	// 돈 투입 대기 프롬프트
	public void promptForMoney() {
		System.out.print("돈을 넣어주세요 (종료: exit)> ");
	}

	// 돈 투입 후 계속 여부
	public void promptForMoreMoney() {
		System.out.print("계속 투입하시려면 금액을, 완료하시려면 Enter를 누르세요> ");
	}

	// 음료 선택
	public void promptForProductSelection() {
		System.out.print("음료를 선택해주세요(#1, #2...) 또는 돈을 더 넣어주세요 (취소: -1)> ");
	}

	// 금액 추가 투입 성공 메시지
	public void showMoneyAdded(int total, int added) {
		System.out.printf("투입금액: [%,d원] +%,d%n", total, added);
	}

	// 구매 성공 메시지
	public void showPurchaseSuccess(String productName, int change) {
		System.out.printf("%s를 받으세요. 잔액 %,d원을 돌려드릴게요.%n", productName, change);
	}

	public void showPurchaseFailure(String reason) {
		System.out.printf("구매 실패: %s%n", reason);
	}

	public void showRefund(int amount) {
		System.out.printf("취소되었습니다. 잔액 %,d원을 반환합니다.%n", amount);
	}

	public void showError(String message) {
		System.out.println("오류: " + message);
	}

	public void showExitMessage() {
		System.out.println("프로그램을 종료합니다.");
	}

	public void showAdminModeEnter() {
		System.out.println("== 관리자 모드 진입 ==");
	}

	public void showUserModeEnter() {
		System.out.println("== 사용자 모드로 전환 ==");
	}

	// 관리자 명령
	public void promptAdminCommand() {
		System.out.print("[관리자] 명령 입력 (restock: 재고 채우기, report: 장부 보고, collect: 매출 회수, power: 전원, exit: 관리자모드 나가기)> ");
	}

	// 재고 상태 출력
	public void showInventoryStatus(Collection<Inventory> inventories) {
		System.out.println("\n== 현재 재고 상태 ==");
		if (inventories.isEmpty()) {
			System.out.println("모든 슬롯이 비어있습니다.");
		} else {
			inventories.stream()
				.sorted(Comparator.comparing(inv -> Integer.parseInt(inv.getCode())))
				.forEach(item -> System.out.printf("#%s [%s %,d원 %d개]%n",
					item.getCode(), item.getProduct().getName(),
					item.getProduct().getPrice(), item.getQuantity()));
		}
		System.out.println("---------------------");
	}

	// 매출 회수 완료
	public void showCollectionComplete(int amount) {
		System.out.printf("매출 회수: %,d원 (장부 기록 완료)%n", amount);
	}

	public void showPowerStatus(boolean isPoweredOn) {
		System.out.printf("전원: %s%n", isPoweredOn ? "ON" : "OFF");
	}

	// 장부 출력
	public void showLedgerReport(String report) {
		System.out.println(report);
	}
}
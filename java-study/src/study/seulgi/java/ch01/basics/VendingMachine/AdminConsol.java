package study.seulgi.java.ch01.basics.VendingMachine;

public class AdminConsol {
	// 관리자 콘솔
	// 매출 회수하기, 전원 키고 끄기, 장부 출력하기.
	private final VendingMachine vm;

	public AdminConsol(VendingMachine vm) {
		this.vm = vm;
	}

	// 자판기에 저장된 장부 뽑기
	public String reportAll() {
		return vm.printLedgerAll();
	}

	// 현재매출 회수하고 장부에 기록하기
	public int collect() {
		return vm.collectSalesAndRecord();
	}

	// 전원 키고 끄기.
	public void powerToggle() {
		vm.togglePower();
	}
}

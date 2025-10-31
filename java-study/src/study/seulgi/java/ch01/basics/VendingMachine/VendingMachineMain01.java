// package study.seulgi.java.ch01.basics.VendingMachine;
//
// import java.time.LocalDate;
// import java.time.ZoneId;
// import java.time.format.DateTimeFormatter;
// import java.util.Collection;
// import java.util.Comparator;
// import java.util.Scanner;
//
// public class VendingMachineMain01 {
//
// 	enum Mode { USER, ADMIN }
//
// 	public static void main(String[] args) {
// 		Scanner sc = new Scanner(System.in);
// 		VendingMachine vm = new VendingMachine();
// 		AdminConsol admin = new AdminConsol(vm);
//
// 		Mode mode = Mode.USER;	// 손님 모드로 시작
//
// 		// 시작시 무조건 출력
// 		System.out.println("=====START====");
// 		System.out.println("[지폐전용자판기] 최소투입금액은 1,000원입니다. 1,000원 단위로만 투입 가능합니다.");
// 		System.out.println("사용자 모드: 금액(정수) 투입, 코드(#1/#2/..), -1(잔돈), -99(관리자모드)");
//
// 		while (true) {
// 			try {
// 				if (mode == Mode.USER) {
// 					boolean moneyInserted = vm.getInserted() > 0;
//
// 					if (!moneyInserted) {
// 						// 상태1 돈 투입대기
// 						System.out.printf("투입 금액: [%,d]원%n", vm.getInserted());
// 						System.out.printf("== %s ==%n", LocalDate.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("M월 d일 E요일")));
// 						System.out.println(vm.menu(false));
// 						System.out.print("돈을 넣어주세요 (종료: exit)> ");
//
// 						// 돈 계속 투입하는 루프
// 						while (true) {
// 							String in = sc.nextLine().trim();
//
// 							if (in.equalsIgnoreCase("exit")) {
// 								System.out.println("프로그램을 종료합니다.");
// 								sc.close();
// 								return; // 프로그램 완전 종료
// 							}
//
// 							if (in.equals("-99")) {
// 								if (vm.getInserted() == 0) {
// 									mode = Mode.ADMIN;
// 									System.out.println("== 관리자 모드 진입 ==");
// 									break; // 돈 투입 종료
// 								}
// 								// 관리자 모드 전환 시 돈이 투입되어 있으면 오류 메시지 출력 필요
// 							}
//
// 							if (in.isEmpty()) {
// 								if (vm.getInserted() > 0) {
// 									break; // 돈 투입 완료 -> 음료선택으로
// 								} else {
// 									System.out.print("돈을 넣어주세요 (종료: exit)> ");
// 									continue;
// 								}
// 							}
//
// 							// 돈 금액 검사. 지폐 투입 검사. 무조건 1000원부터
// 							if (isInt(in)) {
// 								int amt = Integer.parseInt(in);
// 								if (amt >= 1000 && amt % 1000 == 0) {
// 									vm.insert(amt);
// 									System.out.println(vm.menu(false));
// 									System.out.printf("투입금액: [%,d원] +%,d%n", vm.getInserted(), amt);
// 									System.out.print("계속 투입하시려면 금액을, 완료하시려면 Enter를 누르세요> ");
// 								} else {
// 									System.out.println("오류: 1,000원 단위의 지폐만 투입 가능합니다.");
// 									System.out.print("다시 입력해주세요> ");
// 								}
// 							} else {
// 								System.out.println("오류: 잘못된 입력입니다. 금액(숫자)을 입력하세요.");
// 								System.out.print("다시 입력해주세요> ");
// 							}
// 						}
//
// 						if (mode == Mode.ADMIN) {
// 							continue; // 메인 루프 재시작 -> 관리자 모드로 진입
// 						}
//
// 					} else { // 돈이 투입된 상태
// 						// 상태2: 음료 선택 대기
// 						System.out.printf("투입 금액: [%,d]원%n", vm.getInserted());
// 						System.out.printf("== %s ==%n", LocalDate.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("M월 d일 E요일")));
// 						System.out.println(vm.menu(true));
// 						System.out.print("음료를 선택해주세요(#1, #2...) 또는 돈을 더 넣어주세요 (취소: -1)> ");
// 						String in = sc.nextLine().trim();
//
// 						if (in.equalsIgnoreCase("exit")) {
// 							System.out.println("프로그램을 종료합니다.");
// 							sc.close();
// 							return;
// 						}
//
// 						if (in.startsWith("#")) {
// 							String code = in.substring(1);
// 							var r = vm.select(code);
// 							if (r.isOk()) {
// 								System.out.printf("%s를 받으세요. 잔액 %,d원을 돌려드릴게요.%n", r.getProductName(), r.getChange());
// 								vm.refund(); // 음료와 잔액을 같이 뱉어내기. 잔돈까지 다줌
// 							} else {
// 								System.out.printf("구매 실패: %s%n", r.getMessage());
// 							}
// 						} else if (isInt(in)) {
// 							int val = Integer.parseInt(in);
// 							if (val == -1) { // 잔돈 반환
// 								int refund = vm.refund();
// 								System.out.printf("취소되었습니다. 잔액 %,d원을 반환합니다.%n", refund);
// 							} else if (val > 0) { // 돈 추가 투입
// 								// 지폐 투입 규칙 검사
// 								if (val >= 1000 && val % 1000 == 0) {
// 									vm.insert(val);
// 								} else {
// 									System.out.println("오류: 1,000원 단위의 지폐만 투입 가능합니다.");
// 								}
// 							} else {
// 								System.out.println("오류: 잘못된 입력입니다.");
// 							}
// 						} else {
// 							System.out.println("오류: 잘못된 입력입니다. 음료 코드는 #1 형식으로 입력하세요.");
// 						}
// 					}
// 				} else {
// 					//========== 관리자 모드========
// 					System.out.print("[관리자] 명령 입력 (restock: 재고 채우기, report: 장부 보고, collect: 매출 회수, power: 전원, exit: 관리자모드 나가기)> ");
// 					String adminCommand = sc.nextLine().trim();
//
// 					switch (adminCommand.toLowerCase()) {
// 						case "exit":
// 							System.out.println("== 사용자 모드로 전환 ==");
// 							mode = Mode.USER;
// 							continue;
// 						case "report":
// 							System.out.println(admin.reportAll());
// 							break;
// 						case "collect":
// 							int collected = admin.collect();
// 							System.out.printf("매출 회수: %,d원 (장부 기록 완료)%n", collected);
// 							break;
// 						case "power":
// 							admin.powerToggle();
// 							System.out.printf("전원: %s%n", vm.isPoweredOn() ? "ON" : "OFF");
// 							break;
// 						case "restock":
// 							while (true) {
// 								System.out.println("\n== 현재 재고 상태 ==");
// 								Collection<Inventory> slots = vm.getInventorySlots();
// 								if (slots.isEmpty()) {
// 									System.out.println("모든 슬롯이 비어있습니다.");
// 								} else {
// 									slots.stream()
// 											.sorted(Comparator.comparing(inv -> Integer.parseInt(inv.getCode())))
// 											.forEach(item -> System.out.printf("#%s [%s %,d원 %d개]%n",
// 												item.getCode(), item.getProduct().getName(),
// 												item.getProduct().getPrice(), item.getQuantity()));
// 								}
// 								System.out.println("---------------------");
// 								System.out.print("재고를 관리할 슬롯 번호(숫자)를 입력하세요 (뒤로가기: exit)> ");
// 								String slotInput = sc.nextLine().trim();
//
// 								if (slotInput.equalsIgnoreCase("exit")) {
// 									break;
// 								}
//
// 								if (!isInt(slotInput)) {
// 									System.out.println("오류: 슬롯 번호는 정수 숫자로 입력해야 합니다.");
// 									continue;
// 								}
//
// 								Inventory inv = vm.getInventory(slotInput);
//
// 								if (inv != null) {
// 									System.out.printf("'%s'는 현재 %d개 있습니다. 몇 개를 추가하시겠습니까? (취소: 0)> ",
// 											inv.getProduct().getName(), inv.getQuantity());
// 									String qtyInput = sc.nextLine().trim();
// 									if (isInt(qtyInput)) {
// 										int qtyToAdd = Integer.parseInt(qtyInput);
// 										if (qtyToAdd > 0) {
// 											vm.addStock(slotInput, qtyToAdd);
// 											System.out.printf("완료: %s 재고가 %d개 되었습니다.%n", inv.getProduct().getName(), vm.getInventory(slotInput).getQuantity());
// 										} else {
// 											System.out.println("취소되었습니다.");
// 										}
// 									} else {
// 										System.out.println("오류: 수량은 숫자로 입력해야 합니다.");
// 									}
// 								} else { // 새 상품 추가
// 									System.out.printf("%s번 슬롯에 새 상품을 추가합니다.%n", slotInput);
// 									System.out.print("음료 이름: ");
// 									String newName = sc.nextLine().trim();
// 									if (newName.isEmpty()) {
// 										System.out.println("이름이 비어있어 취소되었습니다.");
// 										continue;
// 									}
//
// 									int newPrice = -1;
// 									while (true) {
// 										System.out.print("음료 가격: ");
// 										String priceInput = sc.nextLine().trim();
// 										if (isInt(priceInput)) {
// 											newPrice = Integer.parseInt(priceInput);
// 											if (newPrice >= 0) break;
// 											else System.out.println("오류: 가격은 0 이상이어야 합니다.");
// 										} else {
// 											System.out.println("오류: 가격은 숫자로 입력해야 합니다.");
// 										}
// 									}
//
// 									int newQty = -1;
// 									while (true) {
// 										System.out.print("음료 개수 (취소: 0): ");
// 										String qtyInput = sc.nextLine().trim();
// 										if (isInt(qtyInput)) {
// 											newQty = Integer.parseInt(qtyInput);
// 											if (newQty >= 0) break;
// 											else System.out.println("오류: 개수는 0 이상이어야 합니다.");
// 										} else {
// 											System.out.println("오류: 개수는 숫자로 입력해야 합니다.");
// 										}
// 									}
//
// 									if (newQty > 0) {
// 										vm.addNewProduct(slotInput, newName, newPrice, newQty);
// 										System.out.println("완료: 새 상품이 추가되었습니다.");
// 									} else {
// 										System.out.println("취소되었습니다.");
// 									}
// 								}
// 							}
// 							break;
// 						default:
// 							System.out.println("잘못 입력했습니다. 이 명령어들만 입력가능합니다."
// 								+ "[restock(재고추가), report(장부보고), collect(매출 회수), power(자판기 전원), exit(관리자모드 나가기)]");
// 							break;
// 					}
// 				}
// 			} catch (Exception e) {
// 				System.out.println("오류: " + e.getMessage());
// 			}
// 		}
// 	}
//
// 	private static boolean isInt(String s) {
// 		if (s == null || s.isEmpty()) return false;
// 		try { Integer.parseInt(s); return true; } catch (NumberFormatException e) { return false; }
// 	}
// }
//
// // 배열로 음료수를 관리
// // private String[] drinks ={콜라 사이다 환타}
// // private String[] drinks ={100, 12000 , 1300}
// //사람이 자판기 앞에서 서서new
// // (음료수 뭐뭐 있고 얼마다. 돈을 넣너주세여)
// // 돈을 넣는다
// // 버튼 누른ㄴ다
// // 돈 모자르면 잔액부족
// // (님 환타 사려면 돈 더내야됨)
// // 돈 충분하면 음료주 주고 잔돈출력
// // (ㅇㅋ 음료수랑 잔돈줄게~)
//
// // 관리자가 앞에서서 -99 를 입력하면 자판기의 매출을 출력하도록 하는 기능
// // 관리자가 회수를 입력하면 매출이 초기화되고,
// // 관리자의 장부에 (해당날짜, 매출랙) 기록되도록 한다. 로컬타임존 쓰면 될듯?
// // LocalDate today = Localdate.now()
// // LocalDate oneWeeksAgo today.minusWeeks(1)
// // while(today.isBefore((oneWeeksAgo))){
// // 	// 장부에 현재 날짜를 기록 - 매출
// //	관리자가 회수 누르면 장부 입력되고 자판기 초기화되고 일자 넘어감.
//
// // 	onewWeeksAgo = oneWeeksAgo.plusDays(1);
// // }
//
// // 관리자가 보고를 입력하면 장부에 해당하는 내용ㅇ들이 쭉 출력되로록 한다.
// // 관리자도 음료수 살 수 있다.
//
// // 음 .. 그러면 자판기의 온오프버튼이 있어서 이건 관리자만 조작가능하다.
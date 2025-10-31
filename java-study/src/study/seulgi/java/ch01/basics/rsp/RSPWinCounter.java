// package study.seulgi.java.ch01.basics.rsp;
//
// import java.util.InputMismatchException;
// import java.util.Random;
// import java.util.Scanner;
//
// public class RSPWinCounter {
//
// 	enum Move {
// 		SCISSOR(1, "가위"), ROCK(2, "바위"), PAPER(3, "보");
// 		private final int num;
// 		private final String name;
//
// 		Move(int num, String name) {
// 			this.num = num;
// 			this.name = name;
// 		}
//
// 		public String getName() {
// 			return name;
// 		}
//
// 		static Move fromNum(int n) {
// 			switch (n) {
// 				case 1: return SCISSOR;
// 				case 2: return ROCK;
// 				case 3: return PAPER;
// 				default: throw new IllegalArgumentException("1(가위), 2(바위), 3(보) 중 하나만 입력하세요!");
// 			}
// 		}
// 	}
//
// 	static class Judge {
// 		boolean isPlayerWin(Move player, Move com) {
// 			return (player == Move.SCISSOR && com == Move.PAPER)
// 				|| (player == Move.ROCK && com == Move.SCISSOR)
// 				|| (player == Move.PAPER && com == Move.ROCK);
// 		}
// 	}
//
// 	static class Computer {
// 		private final Random random = new Random();
// 		Move getHand() {
// 			int n = 1 + random.nextInt(3);
// 			return Move.fromNum(n);
// 		}
// 	}
//
// 	public static void main(String[] args) {
// 		try (Scanner sc = new Scanner(System.in)) { // 자동으로 close
// 			Computer computer = new Computer();
// 			Judge judge = new Judge();
//
// 			int streak = 0;
//
// 			while (true) {
// 				Move me = null;
// 				while (me == null) {
// 					System.out.print("내 선택 (1:가위, 2:바위, 3:보) : ");
// 					try {
// 						int n = sc.nextInt();
// 						me = Move.fromNum(n);
// 					} catch (InputMismatchException e) {
// 						System.out.println("숫자만 입력하세요.");
// 						sc.next(); // 잘못된 입력 제거
// 					} catch (IllegalArgumentException e) {
// 						System.out.println(e.getMessage());
// 					}
// 				}
//
// 				Move comp = computer.getHand();
// 				System.out.printf("나 : %s\n", me.getName());
// 				System.out.printf("컴퓨터 : %s\n", comp.getName());
//
// 				if (me == comp) {
// 					System.out.println("비겼습니다. 연승은 유지되고 게임을 계속합니다.\n");
// 					continue;
// 				}
//
// 				if (judge.isPlayerWin(me, comp)) {
// 					streak++;
// 					System.out.printf("플레이어가 이겼습니다! 현재 연승: %d\n\n", streak);
// 				} else {
// 					System.out.println("컴퓨터가 이겼습니다. 게임을 종료합니다.");
// 					System.out.printf("최종 연승: %d\n", streak);
// 					break;
// 				}
// 			}
// 		} catch (Exception e) {
// 			System.out.println("예기치 못한 오류가 발생했습니다: " + e.getMessage());
// 		}
// 	}
// }
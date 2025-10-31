package study.seulgi.java.ch01.basics.answer;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// ===== 도메인 =====
enum Move {
	SCISSORS(1, "가위"),
	ROCK(2, "바위"),
	PAPER(3, "보");

	private final int code;
	private final String label;

	Move(int code, String label) {
		this.code = code;
		this.label = label;
	}
	public int code() { return code; }
	public String label() { return label; }

	public static Move fromCode(int code) {
		for (Move m : values()) if (m.code == code) return m;
		throw new IllegalArgumentException("1(가위), 2(바위), 3(보)만 입력하세요.");
	}

	// 나(this)가 상대(other)를 이기면 true
	public boolean beats(Move other) {
		return (this == SCISSORS && other == PAPER) ||
			(this == ROCK     && other == SCISSORS) ||
			(this == PAPER    && other == ROCK);
	}
}

// ===== 플레이어 =====
interface Player {
	Move pick();
	String name();
}

class HumanPlayer implements Player {
	private final Scanner sc;

	public HumanPlayer(Scanner sc) { this.sc = sc; }

	@Override
	public Move pick() {
		while (true) {
			System.out.print("내 선택 (1:가위, 2:바위, 3:보) : ");
			try {
				int n = sc.nextInt();
				return Move.fromCode(n);
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
				sc.next(); // 잘못된 토큰 소진
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public String name() { return "나"; }
}

class ComputerPlayer implements Player {
	private final Random rnd = new Random();

	@Override
	public Move pick() {
		int n = 1 + rnd.nextInt(3); // 1~3
		return Move.fromCode(n);
	}

	@Override
	public String name() { return "컴퓨터"; }
}

// ===== 심판 =====
enum Result { WIN, LOSE, DRAW }

class Referee {
	public Result judge(Move me, Move comp) {
		if (me == comp) return Result.DRAW;
		return me.beats(comp) ? Result.WIN : Result.LOSE;
	}
}

// ===== 게임 진행 =====
class Game {
	private final Player human;
	private final Player computer;
	private final Referee referee;

	public Game(Player human, Player computer, Referee referee) {
		this.human = human;
		this.computer = computer;
		this.referee = referee;
	}

	public void run() {
		int streak = 0;

		while (true) {
			Move myMove = human.pick();
			Move comMove = computer.pick();

			System.out.printf("나 : %s\n", myMove.label());
			System.out.printf("컴퓨터 : %s\n", comMove.label());

			Result r = referee.judge(myMove, comMove);

			if (r == Result.DRAW) {
				System.out.println("비겼습니다. 연승은 유지되고 게임을 계속합니다.\n");
				continue;
			}
			if (r == Result.WIN) {
				streak++;
				System.out.printf("플레이어가 이겼습니다! 현재 연승: %d\n\n", streak);
				continue;
			}
			// LOSE
			System.out.println("컴퓨터가 이겼습니다. 게임을 종료합니다.");
			System.out.printf("최종 연승: %d\n", streak);
			break;
		}
	}
}

public class RockSP {

}

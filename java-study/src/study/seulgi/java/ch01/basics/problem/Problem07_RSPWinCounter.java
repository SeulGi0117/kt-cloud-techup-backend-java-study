package study.seulgi.java.ch01.basics.problem;

import java.util.Arrays;
import java.util.Random;

public class Problem07_RSPWinCounter {
	enum RSP {ROCK, SCISSORS, PAPER}

	public static RSP changeChar(char c) {
		switch (c) {
			case 'R':
				return RSP.ROCK;
			case 'S':
				return RSP.SCISSORS;
			case 'P':
				return RSP.PAPER;
			default:
				throw new IllegalArgumentException("허용되지 않는 문자. R, S, P 중 하나 입력.");
		}
	}

	public static boolean isWin(RSP me, RSP cp) {
		if (me == RSP.ROCK && cp == RSP.SCISSORS) {
			return true;
		} else if (me == RSP.SCISSORS && cp == RSP.PAPER) {
			return true;
		} else if (me == RSP.PAPER && cp == RSP.ROCK) {
			return true;
		} else
			return false;
	}

	public static RSP computerChoice() {
		Random rand = new Random();
		int r = rand.nextInt(3);

		switch (r) {
			case 0:
				return RSP.ROCK;
			case 1:
				return RSP.SCISSORS;
			case 2:
				return RSP.PAPER;
			default:
				throw new IllegalStateException();
		}
	}

	public static int[] winsByRound(char[] me) {
		int m = me.length;
		int[] wins = new int[m];

		for (int i = 0; i < m; i++) {
			RSP my = changeChar(me[i]);
			RSP cp = computerChoice();

			if (isWin(my, cp)) {
				wins[i] = 1;    // true 면 1추가
			} else {
				wins[i] = 0;    // false 면 0추가
			}

		}
		return wins;
	}

	public static int maxConsecutiveWins(int[] wins) {
		int cnt = 0, max = 0;
		for (int i = 0; i < wins.length; i++) {
			if (wins[i] == 1) {
				cnt++;
				if (cnt > max) {
					max = cnt;
				}
			} else {
				cnt = 0;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		char[] me = {'R', 'S', 'S', 'P', 'P', 'R'};

		int[] wins = winsByRound(me);

		// 라운드 별 승리 여부 [0, 1, 1, 1, 0, 0]
		System.out.println(Arrays.toString(wins));

		// 최대 연속 승수 ( 3 )
		System.out.println(maxConsecutiveWins(wins));
	}
}

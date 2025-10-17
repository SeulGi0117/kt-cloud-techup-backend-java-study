package study.seulgi.java.ch01.basics.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// 1~45까지의 랜덤한 정수를 6개 출력한다.
// 중복 노노 Set은 안쓴다.
// 한개 이상의 함수로 나눠서 while문 안쓰고 개선

// 로직
// 45까지 들어있는 상자
// 사람이 하나 꺼냄. -> 하나 리스트에 담음, 6번 반복
// 만약 중복이 있다면? 버리고 다시 공 뽑음.
// 공들어 있는 상자 한번 섞는 것도 있으면 되나 흠.. 흐음......

public class Lotto2 {
	static class BallBox {
		private List<Integer> balls = new ArrayList<>();

		public BallBox() {
			for (int i = 1; i <= 45; i++) {
				balls.add(i);
			}
		}

		// 상자에서 공 섞기
		public void shake() {
			Collections.shuffle(balls);
		}

		// 공 뽑아서 꺼낸 공 제거
		public int pick() {
			Random rand = new Random();
			int i = rand.nextInt(balls.size());
			return balls.remove(i);
		}
	}

	// 로또 머신 공 뽑기
	static class LottoMachine {
		private BallBox box;

		public LottoMachine(BallBox box) {
			this.box = box;
		}

		public int[] drawNumbers() {
			int[] result = new int[6];
			for (int i = 0; i < 6; i++) {
				result[i] = box.pick();
			}
			Arrays.sort(result);
			return result;
		}
	}

	public static void main(String[] args) {
		System.out.println("로또 추첨기");
		BallBox box = new BallBox();
		box.shake();  // 공섞기

		LottoMachine machine = new LottoMachine(box);
		int[] numbers = machine.drawNumbers();

		System.out.println("당첨 번호: " + Arrays.toString(numbers));

	}

}

/*
public class LottoMain {
	public static void main(String[] args) {
		// 1~45까지의 랜덤한 정수를 6개 출력한다.
		// 대신 중복된 숫자는 없어야 한다.
		// Set은 쓰면 안된다.

		// 1~45까지 1세트
		// 통안에는 20세트의 숫자가 들어가 있다고 가정
		// 큰공안에서 작은공이 툭 튀어나와서 굴러옴
		// 저희가 6개를 꺼내야함
		// 처음에 1이나옴 두번째 1이나왓음
		// 버리고 다시 뽑기
		// 처음이랑 같으면 다시 뽑기
		// 6개가 될때까지 반복

		// 추첨된 공을 담을 그릇
		int[] lotto = new int[6];
		Random random = new Random();

		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = pick(random, lotto, i);
		}

		System.out.println(Arrays.toString(lotto));
	}

	// 여기서 애초에 중복되지않은 숫자를 리턴해주고 싶음
	public static int pick(Random random, int[] lotto, int i) {
		int number = random.nextInt(6) + 1;

		for (int j = 0; j < i; j++) {
			if (lotto[j] == number) {
				return pick(random, lotto, i);
			}
		}
		return number;
	}
}
 */
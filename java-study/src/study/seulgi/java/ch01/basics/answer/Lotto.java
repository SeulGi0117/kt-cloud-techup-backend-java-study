package study.seulgi.java.ch01.basics.answer;

import java.util.Arrays;
import java.util.Random;

public class Lotto {
	public static void main(String[] args) {
		// 1~45까지의 랜덤한 정수를 6개 출력한다.
		// 중복 노노 Set은 안쓴다.

		System.out.println("로또 추첨기");
		Random rand = new Random();
		int[] lottery = new int[6];
		int count = 0;

		while (count < 6) {
			int num = rand.nextInt(45) + 1;

			boolean isDuplicate = false;
			for (int i = 0; i < count; i++) {
				if (lottery[i] == num) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				lottery[count] = num;
				count++;
			}
		}

		Arrays.sort(lottery);
		System.out.println(Arrays.toString(lottery));
	}

	// TreeSet<Integer> lottery = new TreeSet<>();
	// System.out.println("로또 추첨기");
	// Random rand = new Random();
	// int n = 0;
	// while (lottery.size() < 6) {
	// 	int num = rand.nextInt(45) + 1;
	// 	lottery.add(num);
	// }
	// System.out.println(lottery);
}


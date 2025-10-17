package study.seulgi.java.ch01.basics.problem;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Problem05_LotteryNumGene {
	public static void main(String[] args) {
		Set<Integer> lottery = new TreeSet<>();
		Random rand = new Random();

		int n = 0;

		while (lottery.size() < 6) {    // 숫자가 랜덤하게 뽑힐때마다 lottery에 추가되기 때문에 6개가 뽑힐때까지 반복
			int num = rand.nextInt(45) + 1;
			lottery.add(num);
		}
		System.out.println(lottery);
	}
}

// TreeSet, HashSet은 중복을 허용하지 않기 떄문에 while문의 조건식을 바꾸어야 한다.
// n=0을 n++하면 숫자 6개 이하가 나올수도 있음.
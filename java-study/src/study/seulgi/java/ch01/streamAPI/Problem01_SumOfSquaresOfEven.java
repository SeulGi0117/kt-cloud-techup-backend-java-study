package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;

public class Problem01_SumOfSquaresOfEven {
	static int sumOfSquaresOfEven(List<Integer> nums) {
		// 아래의 로직을 Stream으로 수정할 것.
		int sum = 0;
		//        for (int n:nums) {
		//            if (n%2==0){
		//                int sq = n*n;
		//                sum +=sq;
		//            }
		//        }
		//        return sum;
		var SumSquares = nums.stream().filter(n -> n % 2 == 0).
			map(n -> n * n)
			.reduce(0, (a, b) -> a + b);
		return SumSquares;

	}

	public static void main(String[] args) {
		System.out.println(sumOfSquaresOfEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
		// 기대: 4^2 + 2^2 + 6^2 = 56
	}

}

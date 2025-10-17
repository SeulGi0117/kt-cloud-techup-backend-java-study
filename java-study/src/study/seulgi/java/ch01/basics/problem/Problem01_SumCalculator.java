package study.seulgi.java.ch01.basics.problem;

public class Problem01_SumCalculator {
	public static void main(String[] args) {
		int n = 10;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}

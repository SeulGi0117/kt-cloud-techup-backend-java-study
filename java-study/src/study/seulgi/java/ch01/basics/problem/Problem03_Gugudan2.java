package study.seulgi.java.ch01.basics.problem;

public class Problem03_Gugudan2 {
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			for (int j = 2; j < 10; j++) {
				System.out.print(j + " x " + i + " = " + i * j + " ");
			}
			System.out.println();
		}
	}
}

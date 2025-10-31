package study.seulgi.java.ch01.basics.answer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BMIMainAnswer {
	public static double calculateBMI(double w, double h) {
		return w / Math.pow(h / 100, 2);
	}

	public static String resultBMI(double bmi) {
		if (bmi < 18.5) return String.format("결과 : 저체중 (BMI: %.1f)", bmi);
		else if (bmi < 23) return String.format("결과 : 정상 (BMI: %.1f)", bmi);
		else if (bmi < 25) return String.format("결과 : 비만전단계 (BMI: %.1f)", bmi);
		else if (bmi < 30) return String.format("결과 : 1단계 비만 (BMI: %.1f)", bmi);
		else if (bmi < 35) return String.format("결과 : 2단계 비만 (BMI: %.1f)", bmi);
		else return String.format("결과 : 3단계 비만 (BMI: %.1f)", bmi);
	}

	public static String inputName(Scanner scanner) {
		while (true) {
			try {
				System.out.print("이름 (한/영, 공백없이 2~10자): ");
				String name = scanner.next();
				if (name.length() >= 2 && name.length() <= 10 && name.matches("^[A-Za-z가-힣]+$")) {
					return name;
				}
				throw new IllegalArgumentException("잘못 입력했습니다. 공백 없는 2~10자 한글/영문 이름을 입력하세요.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static String inputGender(Scanner scanner) {
		while (true) {
			try {
				System.out.print("성별 (남자/여자): ");
				String g = scanner.next();
				if (g.equals("남자") || g.equals("여자")) return g;
				if (g.equals("남")) return "남자";
				if (g.equals("여")) return "여자";
				throw new IllegalArgumentException("잘못 입력했습니다. '남자' 또는 '여자'로 입력하세요.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static double inputHeight(Scanner scanner) {
		while (true) {
			try {
				System.out.print("키(cm) (10~250): ");
				double height = scanner.nextDouble();
				if (height >= 10 && height <= 250) {
					return height;
				}
				throw new IllegalArgumentException("잘못 입력했습니다. 10~250 사이의 값을 입력하세요.");
			} catch (InputMismatchException e) {
				System.out.println("숫자로 입력하세요. 예) 175.5");
				scanner.next(); // 잘못된 입력을 소진
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static double inputWeight(Scanner scanner) {
		while (true) {
			try {
				System.out.print("몸무게(kg) (2~300): ");
				double weight = scanner.nextDouble();
				if (weight >= 2 && weight <= 300) {
					return weight;
				}
				throw new IllegalArgumentException("잘못 입력했습니다. 2~300 사이의 값을 입력하세요.");
			} catch (InputMismatchException e) {
				System.out.println("숫자로 입력하세요. 예) 70.2");
				scanner.next(); // 잘못된 입력을 소진
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void printResult(String name, String gender, double height, double weight, double bmi) {
		System.out.printf("""
		이름 : %s
		성별 : %s
		키 : %.1fcm
		몸무게 : %.1fkg
		%s
		""", name, gender, height, weight, resultBMI(bmi));
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			String name = inputName(scanner);
			String gender = inputGender(scanner);
			double height = inputHeight(scanner);
			double weight = inputWeight(scanner);

			double bmi = calculateBMI(weight, height);
			printResult(name, gender, height, weight, bmi);
		}
	}
}
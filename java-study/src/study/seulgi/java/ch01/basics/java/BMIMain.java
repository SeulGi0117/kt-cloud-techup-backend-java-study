package study.seulgi.java.ch01.basics.java;
import java.util.*;
// BMI : Body Mass Index
// 체질량 지수
// 몸무게(kg) / 키(m)키(m)

// 1. 키와 몸무게, 성별, 이름을 입력받아서
//     BMI지수를 계산하고
//     BMI 지수에 따른 결과를 출력 / 성남시청 기준.

// 출력에시
// 이름 : 홍길동
// 성별 : 남자
// 키 : 175.5cm
// 몸무게 : 70.2kb
// BMI 지수: 22.8
// 결과 : 정상
public class BMIMain {

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
	public static void inputAndPrintResult() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = scanner.next();

		System.out.print("성별 : ");
		String gender = scanner.next();

		System.out.print("키(cm) : ");
		double height = scanner.nextDouble();

		System.out.print("몸무게(kg) : ");
		double weight = scanner.nextDouble();

		double bmi = calculateBMI(weight, height);
		System.out.println(resultBMI(bmi));
	}

	public static void main(String[] args) {
		inputAndPrintResult();
	}
}
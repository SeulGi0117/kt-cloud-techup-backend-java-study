package study.seulgi.java.ch01.basics.problem;

import java.util.Arrays;

public class Problem04_RefValueTest {
	static void plusFive(int v) {
		v += 5; // 기본형
	}

	static void modifyArr(int[] a) {
		a[1] = 77;  // 내부 변경
	}

	static void changeRef(int[] a) {
		a = new int[] {9, 9}; // 재할당
	}

	public static void main(String[] args) {
		int x = 1;
		plusFive(x);
		System.out.println("x=" + x); // 예상:6

		int[] arr = {10, 20, 30};
		modifyArr(arr);
		System.out.println("arr=" + Arrays.toString(arr));   // 예상: 10, 77, 30

		changeRef(arr);
		System.out.println("arr=" + Arrays.toString(arr)); // 예상: 9, 9

	}
}
/*
결과:
x=1
arr=[10, 77, 30]
arr=[10, 77, 30]

x는 값복사가 이루어져 지역변수 v에게만 +5가 되기 때문에 x는 여전히 1이 나온다.
modifyArr 은 배열이 같은 메모리 주소값을 참조하고 있기 때문에, 힙 객체의 내부 값이 변경되어 arr 또한 값이 변경 된다.
changeRef에서 a또한 arr의 참조를 받았지만 메소드 내에서 새로 정의를 내렸기때문에,
지역적ㅇ니 부분에서만 9, 9라는 값을 가리키게 되고, 실제 arr은 기존의 값을 계속 가리키게 된다.
*/


package study.seulgi.java.ch01.basics.generic;

// 메소드에 제네릭을 써서 어떤 타입이 오든 출력하는 유틸리티 함수
public class SoutUtil {
	// static을 쓸건데, 이런 유틸리티 함수들은 쓸때마다 new 해버리면 자꾸 생성된다.
	// 값이 변경되는것도 아니라서 static으로 씀
	// 메서드는 함수 반환하는 곳에(반환타입) 앞에다가 <T>를 작성해야 한다. 문법이라 그냥 외워야됨

	// public static <E> void print(E array){
	//	E 적으면 반환타입 앞에도 E를 적어야함
	// }

	public static <T> void print(T[] array){
		// T[] 배열로 받아야 한다.
		for(T element: array){
			System.out.println(element);
		}

	}
}

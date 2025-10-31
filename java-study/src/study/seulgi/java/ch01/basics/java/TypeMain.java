package study.seulgi.java.ch01.basics.java;

public class TypeMain {
	public static void main(String[] args) {
		// 기본 자료형( primitive type)
		// 참조 자료형(reference type)
		// Integer, Deouble, Boolean, String

		// 어떤 메모리에 위ㅣ하느냐에 따라 다름

		// 기본자료형 => stack 메모리에 위치
		// 참조자료형 => heap 메모리에 위치

		// 기본 값이 다르다.
		// int의 기본값 0.

		// int a1 = 1;
		// Integer a2 = 2;

		// 기본값이 null이다. 이걸 생각하고 있어야함. 기본적으로 프리미티브 타입 기본자료형을 써야한다.
		// 쓰다가 이거 null 일수있는데? 어 이거 generic으로 들어온건데? 하면 참조자료형을 써야하는 상황이 온다.
		// 그러면 이런 primitive type을 우선적으로 사용하되, 특별한 경우에만 reference type을 사용하자.
		// primitive type이 성능을 더 많이 안잡아 먹는데, heap 영역을 잡아먹는거 자체가영향을 많이 먹는다.
		// 특별한 경우에만 참조형으로 사용하자.
	}
}

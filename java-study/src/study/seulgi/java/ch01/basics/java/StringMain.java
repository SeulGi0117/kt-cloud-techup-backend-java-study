package study.seulgi.java.ch01.basics.java;

public class StringMain {
	// 엔트리 포인트 처럼 진입점이 있어야한다.
	// JVM이 어디서부터 코드를 실행시킬지 입구가 있어야한다. 이걸 엔트리 포인트라고 불린다.
	// JVM이 코드를 실행시킬때 무조건 이 main 메서드부터 찾아서 그 안에 있는 코드를 찾아 실행시킨다.
	// 나는 여기서 부터 코드 실행을 시작할거야를 알려주기 위해 mian 메서드 안에 쓰는것이다.

	// 여기서 이 밖에서는안되고 왜 꼭 public static void main 메서드 안에 써야하는지? 신입 단골 질문
	public static void main(String[] args) {
		// String :  문자열을 다루 클래스
		String str1 = "Hello World!" + 1;
		System.out.println(str1);
		String str2 = new String("Hello World!");
		// 원래는 이렇게 new 키워드를 붙여서 써야하지만 스트링은 하도 많이 쓰니 str1처럼 해도 됨

		//자바는 타입에 엄격하다보니 앞의 타입과 뒤의 타입이 일치해야한다.
		// Integer str3 = "Hello World!" + '!!!'; // 이렇게 구라치면 컴파일한테 걸림
		var a = "1" + 1;
		// var b = a instanceof Integer;

		// System.out.println(b);
	}
}

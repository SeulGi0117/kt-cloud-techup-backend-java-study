package study.seulgi.java.ch01.basics.java;

public class VariableMain {
	public static void main(String[] args) {
		// 변수: 데이터를 저장하는 공간
		// 밥 먹을때 밥은 밥그릇에 뜨고 국은 국그릇에 뜬다. 음식 데이터, 그릇 변수
		// 변수 선언 초기화 할당 3가지의단계가 있다

		// 자료형 변수의이름 = 값 // 이런식으로 사용하게 된다
		// 자료형 변수의 이름 = 값 데이터
		// 그릇 밥그릇 = 밥
		// = 대입 연산자 : 좌항에 우항을 대입한다. 저장한다.
		int a; // 변수선언
		a = 0; // 초기화
		a = 10; // 할당

		int b = 20; // 선언+초기화+할당 동시에

		// var 는 뭔가요? => 타입추론(컴파일러가 타입을 추론)
		// var a; 이렇게 추론 안되는 타입은 사용 불가. 꼭 초기화 해줘야하고
		// var는 지역변수로만 활용가능하다.
		var a1 = 10;
		System.out.println(a);

	}
}

package study.seulgi.java.ch01.basics.java;

public class ConditionMain {
	public static void main(String[] args) {
		// 조건문: if, switch
		// jdk 14부터는 switch문을 더 잘 씀(개인차)
		// 조건문, 반복문.. 00문 -> 문법(grammar statement) -> 변수에 담을 수 없음
		// switch문 문법에서 표현식으로 바뀜 => 변수에 담기 가능
		// 그래서 조건문을 쓸때 switch 사용하는 버릇을 들이면 코틀린에서 사용하기 쉽다
		// kotlin에서는 if 문이 표현 식이다. => when, if

		// lazy evaluation(Short-circuit evaluation) - 게으른 연산
		// or은 앞이 ture면 뒤를 안보고, and는 앞이 false일때 뒤를 안봄
		// false(db에 한번 무조건 갔다워야함.) || ture => 이것보단 ture || false 로 쓰는게 나음

		// if 문은 대체 언제쓰나요?
		// 객체 지향은 개발을 모르는 사람이 내 코드를 봐도 대략적인 플로우나 의도는 알아야한다.

		// 만약에 ~~라면
		// 만약에 로그인 한 사용자가 관리자라면 ~~한다.

		/*
		if(로그인 한 사용자가 관리자){
			관리자 기능 제공
		} else {
			일반 사용자 기능 제공
		}*/

		// 요지랄로 if문 사요하면 미친 재할당을 사용하게 된다. seems like 정합 알고리즘 코드 짜는데 if문 떡칠한 나처럼...

	}
}

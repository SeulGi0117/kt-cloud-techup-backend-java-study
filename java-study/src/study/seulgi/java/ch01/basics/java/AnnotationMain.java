package study.seulgi.java.ch01.basics.java;

public class AnnotationMain {
	public static void main(String[] args) {
		// 코드 설명이나 뭐 노트 식으로 주석처리 하게 된다. 주석엔여러종류가 있다.
		// java doc 주석
		// todo 주석
		// fixme 주석
		// 일반 주석
		/* 여러줄 주석*/

		// 한줄주석(일반주석)
		/** 여러 줄 주석
		 *
		 * 엔터 칠대마다 별이 늘어나며 여러줄주석이 됨
		 * */

		// TODO: 2025.10.13. 어떤 작업이 끝나고 나면 이어서 작업 - SeulGi 강사님은 개발 닉네임까지 쓰는편
		// 투두 하면 투두만 모아 볼수 있는데 있음. 툴팁에서 모아서 볼수있다.

		// FIXME: 2025.10.13. 어떤 작업이 잘못되어서 고쳐야함. -Seulgi 이렇게 색깔이 강조되어서 나옴.

		// java doc 주석
		String str = "Hello";
		// String에 마우스 올리면 나타나는 설명창 처럼 내가 만든 함수나 변수에 설명 달수 있음.
		// new MemberService("슬기", 10);
	}
}

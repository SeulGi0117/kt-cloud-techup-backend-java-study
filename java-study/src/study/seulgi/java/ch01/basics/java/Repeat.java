package study.seulgi.java.ch01.basics.java;

import java.util.List;

public class Repeat {
	public static void main(String[] args) {
		// 실무에서 제일 많이 쓰이는건 foreach
		// foreach 향상된 for문: 배열이나 컬렉션을 다룰 때 주로 사용.
		// 하나씩 꺼내서 쓸때
		List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

		// for(숫자하나:리스트(컬렉션_){...}
		for (int n : list) {
			System.out.print("" + n);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println("" + list.get(list.size()));
		}

		// 가장 기본적인 for문
		for (int i = 0; i <= 10; i++) {
			System.out.println(" " + i);
		}

		// while 문은 조건이 명확할 때 -> 어떤 조건을 검사해서 ture면 반복, false면 빠져나옴
		// 실무에서 사용성이 좋진 않은것같다 많이 써본적은 없다.
		int i = 0;
		while (i < 3) {
			System.out.println("Hello World!");
			i++;
		}
		// 실무에서 잘 안쓰는 이유: 무한루프에 빠질 가능성, break 포인트가 없다면 계속 돈다.

		// do-while
		// 근데 실무에서 쓸일이 잘 없다
		do {
			System.out.println("dodo");
		} while (i < 1);
		{
			i++;
		}
	}
}

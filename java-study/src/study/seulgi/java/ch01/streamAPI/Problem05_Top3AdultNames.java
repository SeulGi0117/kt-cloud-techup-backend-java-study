package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;

public class Problem05_Top3AdultNames {
	static class Person {
		final String name;
		final int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}

	// 아래 로직을 Stream으로 수정할 것

	// 조건: age >= 20 인 사람만 대상으로 나이 내림차순 정렬, 상위 3명의 이름리스트를 반환.
	static List<String> top3AdultNames(List<Person> people) {
		var adults = people.stream()
			.filter(p -> p.age >= 20)
			.sorted((a, b) -> Integer.compare(b.age, a.age))
			.limit(3)    // Stream에서 상위 3개만 남기기는 limit()을 사용한다
			.map(p -> p.name)
			.toList();
		return adults;

		// List<Person> adults = new ArrayList<>();
		// for (Person p : people) {
		// 	if (p.age >= 20) {
		// 		adults.add(p);
		// 	}
		// }
		// adults.sort((a, b) -> Integer.compare(b.age, a.age));
		//
		// List<String> result = new ArrayList<>();
		// for (int i = 0; i < adults.size() && i < 3; i++) {
		// 	result.add(adults.get(i).name);
		// }
		// return result;
	}

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
			new Person("Amy", 19),
			new Person("Bob", 22),
			new Person("Cody", 31),
			new Person("Daisy", 44),
			new Person("Evan", 18)
		);
		System.out.println(top3AdultNames(people));
		// 기대 : [Daisy, Cody, Bob,]
	}
}

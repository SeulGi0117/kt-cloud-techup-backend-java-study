package com.kt.pracitce.stream;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain {
	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
		// names.add("Frank"); // 당연히 오류남. list는 불변이다.

		// 알파벳 e가 들어간 이름들만 모아서 출력 (filter를 사용해서 출력 가능)
		var filterNames = names.stream()
			.filter(name -> name.contains("e"))  // name 단수를 쓰는 것이 관례이다. contaons 포함되다 라는 뜻
			.collect(Collectors.toList()); // 이건 사람들이 너무 많이써서 jdk16부터 .toList()라고 사용 가능하게 바뀌었다

		// 포함되면 true라는 뜻. e가 포함된것은 포함되게 된다. 포함 안되면 버려지게 됨.
		// var로 하면 객체가 stream으로 되어있다. 중간 연산까지만 한것이기 떄문이다.

		System.out.println(filterNames);

		var mapNames = names.stream()
			// .map(name -> name.toUpperCase())
			.map(String::toUpperCase) // 메소드 참조로 바꾼 버전
			.toList();
		System.out.println("원본 리스트: " + names);
		System.out.println("Map: " + mapNames);

		// flatMap
		var numbers = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
		var flatMapNumbers = numbers.stream()
			// .flatMap(number -> number.stream())
			.flatMap(Collection::stream) // 이렇게 사용도 가능. 메서드 리퍼런스
			.toList();

		System.out.println(numbers);
		System.out.println("flatMap: " + flatMapNumbers);

		// limit 2개로 자르는거. 뭐 제한걸어서 배열 나누기
		var limitedNames = names.stream().limit(2).toList();
		System.out.println(limitedNames);

		// skip 리밋과 같이 쓰임
		var skipNames = names.stream().skip(2).toList();
		System.out.println(skipNames);

		// sort 정렬
		List<String> names2 = List.of("James", "Alice", "Bob", "Charlie", "Zone", "Eve");

		var sortedNames = names2.stream().sorted().toList(); // 기본은 오름차순
		// sorted에서 ()인자가 있는것과 없는것이 있다. => 역정렬
		// Comparte라는 것이 있다.
		var descNames = names2.stream().sorted(Comparator.reverseOrder()).toList();

		System.out.println(sortedNames);
		System.out.println(descNames);

		// forEach
		names2.stream()
			.sorted()
			// .forEach(name -> System.out.println(name));
			.forEach(System.out::println);  // 참조로 쓸수도 있다.

		// Distinct 중복제거
		var duplicatedNames = List.of("Alice", "Alice", "Bob", "Charlie", "Bob", "Eve");
		var duplicatedNames1 = duplicatedNames.stream().distinct().toList();
		System.out.println(duplicatedNames1);

		var duplicatedNames2 = duplicatedNames.stream().collect(Collectors.toSet());
		System.out.println(duplicatedNames2);

		// findFirst
		var findFirstName = names.stream().findFirst().get();
		System.out.println(findFirstName);
		// get 에러내기
		List<String> names3 = List.of();
		var findFirstName2 = names3.stream().findFirst().orElse("초기값");
		System.out.println(findFirstName2);

		// fineAny
		var findAnyName = names.stream().findAny().get();
		System.out.println("FindAny: " + findAnyName);

		// match는 allMatch, anyMatch, noneMatch
		// 일치하는 값이면 boolean으로 반환.
		// 이거 있어 없어? 궁금할때 사용함.
		var allMatchNames = names.stream()
			.allMatch(name -> name.equals("Alice")); // 지금은 문자를 썻지만 보통 정규식을 사용해서 이것에 부함되는게 있아? 하는 형식으로 사용
		System.out.println(allMatchNames);

		var anyMatchNames = names.stream()
			.anyMatch(name -> name.equals("Alice")); // 지금은 문자를 썻지만 보통 정규식을 사용해서 이것에 부함되는게 있아? 하는 형식으로 사용
		System.out.println(anyMatchNames);

		var noneMatchNames = names.stream()
			.noneMatch(name -> name.equals("Alice")); // 지금은 문자를 썻지만 보통 정규식을 사용해서 이것에 부함되는게 있아? 하는 형식으로 사용
		System.out.println(noneMatchNames);

		// reduce 총합을 구할때 많이 사용
		var numbers2 = List.of(1, 2, 3, 4, 5);
		var sum = numbers2.stream()
			.reduce(Integer::sum)  // 여기가 Integer::sum이라는 뜻은 람다식이라는 뜻이다.
			.get(); // get을 사용한다는건 Optional이라는뜻
		System.out.println(sum);

		// 여기까지는 중간연산들에서 대해 알아보았다.
		// ============================================
		//
	}

}

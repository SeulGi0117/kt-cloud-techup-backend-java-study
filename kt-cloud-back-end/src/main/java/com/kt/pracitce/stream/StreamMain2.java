package com.kt.pracitce.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMain2 {
	public static void main(String[] args) {
		// stream 연산의 최종연산들

		List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

		// 최종연산 Collectors을 이용한게 대부분이다.

		// List로 반환하는 최종연산
		var nameList = names.stream().collect(Collectors.toList());
		var nameSet = names.stream().collect(Collectors.toSet());
		var nameMap = names.stream()
			.collect(Collectors.toMap(name -> name, name -> name.length()));
		// map은 키와 value로 이루어져 있기때문에 바로 넣을수 없고 key와 value로 바꾸어서 넣어줘야한다.
		// key는 그대로 name의 name을 가지고 value는 name의 length를 가진다.
		/*
		(name -> name, name -> name.length()));
		return name -> {
		return name 이거 어쩌구라서 } 위와 같이 되는걸 알수 있어야 한다.
		 */
		System.out.println(nameMap); // 글자수와 맵핑되어서 출력된다.

		// 어떤 ㅂ열의 총갯수
		var c1 = names.stream().count();
		// count는 원래
		var c2 = names.stream().collect(Collectors.counting());
		// 원래 이거 였으나 하도 많이 써서 toList처럼 count()로 사용가능
		System.out.println(c1);
		System.out.println(c2);

		// joining -> String
		var joining1 = names.stream().collect(Collectors.joining());
		var joining2 = names.stream().collect(Collectors.joining(", "));
		var joining3 = names.stream().collect(Collectors.joining(", ", "[", "]"));
		// prefix랑 suffix 지정 가능
		System.out.println(joining1);    // 출력 결과 AliceBobCharlieDavidEve
		System.out.println(joining2);    // 출력 결과 Alice, Bob, Charlie, David, Eve
		System.out.println(joining3);    // 출력 결과 [Alice, Bob, Charlie, David, Eve]

		// 평균 합계 구하기
		var sumLength = names.stream()
			.collect(Collectors.summingInt(name -> name.length()));
		// int 형태로 name의 글자수를 다 더해줘
		System.out.println(sumLength);

		var avgLength = names.stream().collect(Collectors.averagingDouble(name -> name.length()));
		System.out.println(avgLength);

		// 최대값 최소값
		// minBy는 최소값을 가져오는건데, comparingInt해서 가장 작은 값을 가져온다.
		// Optional이다.
		var minLength = names.stream().collect(Collectors
			.minBy(Comparator
				.comparingInt(name -> name.length()))).get(); // get을 안쓰면 Optional[Bob]가 나온다.
		System.out.println(minLength);

		var minLength1 = names.stream().min(Comparator.comparingInt(name -> name.length())).get();
		var maxLength2 = names.stream().max(Comparator.comparingInt(name -> name.length())).get();

		var maxLength = names.stream().collect(Collectors.maxBy(Comparator.comparingInt(name -> name.length()))).get();
		System.out.println(maxLength);

		// 평균을 정수 반올림으로 뽑기
		var ageLengthInt1 = names.stream().collect(Collectors.averagingInt(n -> n.length())).intValue();
		var ageLengthInt2 = Math.round(names.stream().collect(Collectors.averagingInt(n -> n.length())));

		// round는 반올림, ceil은 올림, 버림은 floor
		// 이건 정수에서 버림처림됨.

		// groupingBy
		
	}
}

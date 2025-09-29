package com.kt.pracitce.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamMain3 {
	public static void main(String[] args) {
		List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");

		// groupingBy => 어떤 기준으로 그룹핑해서 Map형태로 반환
		// 기준의 값이 key, 그 값에 해당하는 컬렌션이 value
		var groupingByNames = names.stream().collect(Collectors.groupingBy(name -> name.length()));
		System.out.println(groupingByNames);

		var b = groupingByNames.get(3);
		System.out.println("get3의 결과:" + b);

		var examList = List.of(
			new Exam("철호", 50),
			new Exam("도현", 60),
			new Exam("의진", 10),
			new Exam("동현", 20),
			new Exam("무경", 30),
			new Exam("경호", 80),
			new Exam("종한", 90),
			new Exam("지민", 100),
			new Exam("정수", 60),
			new Exam("예은", 10),
			new Exam("승규", 30),
			new Exam("세현", 50),
			new Exam("슬기", 70),
			new Exam("신영", 90)
		);

		var groupedMap = examList.stream().collect(
			Collectors.groupingBy(exam -> exam.score)
			// score 가 같은 객체끼리 묶어서 Map에 저장한다.
		);
		// System.out.println("score 가 같은 객체끼리 묶어서 Map에 저장한다. " + groupedMap);

		// var groupedMap1 = groupedMap.entrySet().stream()
		// 	.filter(entry -> entry.getKey() >= 90)
		// 	.flatMap(r -> r.getValue().stream())
		// 	.toList();
		// System.out.println("90이상 뽑기 " + groupedMap1);

		var groupedMap2 = examList.stream().collect(Collectors.groupingBy(exam -> exam.score, Collectors.counting()));
		System.out.println("점수별로 몇개인지 카운팅.(몇명인지): " + groupedMap2);

		var groupedMap3 = examList.stream().collect(
			Collectors.groupingBy(
				exam -> exam.score, Collectors.mapping(
					exam -> exam.name, Collectors.toList()
				)
			)
		);
		System.out.println("점수별로 이름 누구인지 출력: " + groupedMap3);

		// var R = examList.stream()
		// 	.collect(Collectors.groupingBy(
		// 		exam -> exam.score,
		// 		TreeMap::new,
		// 		Collector.mapping(
		// 			exam -> exam.name,
		// 			Collectors.toList()
		// 		)
		// 	));
		// treemap 으로 바꾸면 오름차순으로 정렬
		// 정렬이 되는걸 바꿀때는 뭐 트리맵으로 바꾸는걸 알수있다?

		// partitioningBy
		// 조건에 맞는 애들은 true
		var partitionedMap = examList.stream().collect(
			Collectors.partitioningBy(exam -> exam.score >= 60)
		);
		// 60점 이상인 애들은 true. filter에서는 60점 미만이면 버려졌는데,
		// partitioningBy에서는 true, false 로 분류된다.
		System.out.println(partitionedMap);

		// patitioningBy를 이용해 합불여부 만들기

		var passedPerson = partitionedMap.entrySet().stream()
			.filter(entry -> entry.getKey())
			.flatMap(map -> map.getValue().stream())
			.map(exam -> {
				exam.isPass = true;
				return exam;
			}).toList();

		System.out.println("Pass 한사람들: " + passedPerson);

		// summarizingInt
		var summarizingInt = names.stream().collect(Collectors.summarizingInt(String::length));
		System.out.println("summarizingInt결과 : " + summarizingInt);

		// names 배열의 요약본을 만들어주는것이다. 카운트는 5이고.글자수의 총합은 23, 글자수중 가장 작은것은 3, 평균은 4.6이고 제일 글자수 많은ㄱ너 7이다.
	}
}

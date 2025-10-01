package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem06_Top5FrequentWords {

	static List<String> top5FrequentWords(List<String> sentences) {
		// TODO
		/*
		주어진 문장들에서 가장 빈도수가 높은 단어 5개를 반환
		- 대소문자 구분 없음
		- 공백 무시
		Stream API 활용
		*/

		var result = sentences.stream()
			.map(s -> s.toLowerCase().replaceAll("[^a-z\\s]", ""))
			.flatMap(s -> Arrays.stream(s.split("\\s+")))
			.filter(s -> !s.isEmpty())
			.collect(Collectors.groupingBy(
				s -> s, Collectors.counting()))
			.entrySet().stream()

			// 여기서부터는 어려운것 같습니다.
			.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
			.limit(5)
			.map(Map.Entry::getKey)
			.toList();

		return result;
	}

	public static void main(String[] args) {
		List<String> sentences = Arrays.asList(
			"Hello Stream API!",
			"hello  java   stream",
			"Stream... HELLO?"
		);
		System.out.println(top5FrequentWords(sentences));
		// 기대(예): [hello, stream, api, java]
	}
}

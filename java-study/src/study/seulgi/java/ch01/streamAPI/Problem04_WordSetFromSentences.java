package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem04_WordSetFromSentences {
	// 아래 로직을 Stream으로 수정할 것
	// 로직부터 생각해보자.
	//  들어온 리스트를 돌면서 문장을 띄어쓰기 기준으로나눈다
	// 문자를 모두 소문자로 바꾼다
	// s가 비어있지 않을때만 result에 추가한다.

	static Set<String> wordSet(List<String> sentences) {
		// Set<String> result = new HashSet<>();
		// for (String line : sentences){
		// 	for (String w: line.split("\\s+")){
		// 		String s = w.toLowerCase().trim();
		// 		if(!s.isEmpty()){
		// 			result.add(s);
		// 		}
		// 	}
		// }
		// return result;
		var result = sentences.stream()
			.map(s-> s.toLowerCase().trim().split("\\s+"))
			.flatMap(Arrays::stream)	// 배열을 단일 스트림으로 풀어야 한다.
			.filter(s -> !s.isEmpty())
			.collect(Collectors.toSet());

		return result;
	}

	public static void main(String[] args) {
		List<String> sentence = Arrays.asList(
			"Hello Stream API",
			"hello Java stream",
			" "
		);
		System.out.println(wordSet(sentence));
		// 기대: [hello, strea, api, javas] (순서는 Set 특성상 달라질수있다.)
	}
}

package study.seulgi.java.ch01.streamAPI;

import java.util.Arrays;
import java.util.List;

public class Problem02_CleanAndSortNames {
	static List<String> cleanAndSort(List<String> names) {
		// 아래 로직을 Stream으로 수정할 것.
		// Set<String> seen = new HashSet<>();
		// List<String> result = new ArrayList<>();

		// for (String raw : names) {
		//     String s = raw.trim(); // 공백제거
		//     if (s.length() >= 3){   // 길이가 3이상인 문자열만
		//         String up = s.toUpperCase();
		//         if (!seen.contains(up)){    // 지금까지 처리한 결과중에 up있는지 확인
		//             seen.add(up);   // set에 넣어서 중복방지
		//             result.add(up); // 출력 리스트에도 추가
		//         }
		//     }
		// }

		// Collections.sort(result);   // 정렬
		// return result;

		// 조건: 문자열 리스트 names에 대해 조건을 적용하여 리스트로 반환한다.
		// 1. trim으로 앞뒤 공백을 제거한다.
		// 2. 길이 3미만 글자는 제거한다.
		// 3. 모두 대붅로 변환한다.
		// 4. 중복을 제거한다
		// 5. 사전순 정렬

		// 로직부터 생각을 해보자. 이걸 스트림으로 바꾼다면?
		var resultToStream = names.stream()
			.map(s -> s.trim())
			.filter(s -> s.length() >= 3)
			.map(s -> s.toUpperCase())
			.distinct()    // 중복 제거
			.sorted()      // 정렬
			.toList();

		return resultToStream;
	}

	public static void main(String[] args) {
		List<String> in = Arrays.asList(" amy", "BO", " amy", "alice", "AlIce ", "bob ");
		System.out.println(cleanAndSort(in));    // 기대: [ALICE, AMY, BOB]
	}
}

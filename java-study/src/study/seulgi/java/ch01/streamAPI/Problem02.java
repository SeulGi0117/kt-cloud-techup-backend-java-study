package study.seulgi.java.ch01.streamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem02 {
    static List<String> cleanAndSort(List<String> names){
        // 아래 로직을 Stream으로 수정할 것.
        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (String raw : names) {
            String s = raw.trim(); // 공백제거
            if (s.length() >= 3){   // 길이가 3이상인 문자열만
                String up = s.toUpperCase();
                if (!seen.contains(up)){    // 지금까지 처리한 결과중에 up있는지 확인
                    seen.add(up);   // set에 넣어서 중복방지
                    result.add(up); // 출력 리스트에도 추가
                }
            }
        }
        Collections.sort(result);   // 정렬
        return result;
    }

    public static void main(String[] args) {
        List<String> in = Arrays.asList(" amy", "BO", " amy", "alice", "AlIce ", "bob ");
        System.out.println(cleanAndSort(in));    // 기대: [ALCE, AMY, BOB]
    }
}

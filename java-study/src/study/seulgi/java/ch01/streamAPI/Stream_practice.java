package study.seulgi.java.ch01.streamAPI;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public class Stream_practice {
	// ---- 도메인 ----
	static class Student {
		final String name;
		final String dept;					// 부서(전공/반 예: DEV / HR / QA)
		final List<Integer> scores;	// 시험 점수들
		final List<String> courses;	// 수강 과목들

		Student(String name, String dept, List<Integer> scores, List<String> courses){
			this.name = name;
			this.dept = dept;
			this.scores = scores;
			this.courses = courses;
		}

		double avgScore(){
			return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
		}

		@Override
		public String toString() {
			return String.format("%s(%s, avg=%.1f)", name, dept, avgScore());
		}
	}

	// 샘플 데이터
	static List<Student> sample(){
		return Arrays.asList(
			new Student("Amy", "DEV", Arrays.asList(90, 80), Arrays.asList("Java", "Git")),
			new Student("Bob", "DEV", Arrays.asList(70, 100, 85), Arrays.asList("Java", "Spring")),
			new Student("Cara", "HR", Arrays.asList(60, 75), Arrays.asList("Excel", "Communication")),
			new Student("Dan", "HR", Arrays.asList(88, 92), Arrays.asList("Communication", "Leadership")),
			new Student("Eve", "QA", Arrays.asList(90,90), Arrays.asList("Java", "QA"))
		);
	}
	public static void main(String[] args){
		List<Student> students = sample();

		// 1) map: 학생 이름을 대문자로 변환해 리스트로
		System.out.println("1) map -- 이름 대문자 리스트");
		List<String> upperNames = students.stream().map(s->s.name.toUpperCase()).toList();
		System.out.println(" "+upperNames);

		// 2) filter: 평균 점수가 85 이상인 하생만 추려 이름 리스트
		System.out.println("\n2) filter --  평균 >= 85인 학생");
		List<String> topNames = students.stream()
			.filter(s->s.avgScore() >=85)
			.map(s-> String.format(("%s(%.1f)"),s.name, s.avgScore()))
			.toList();
		System.out.println(" "+topNames);

		// 3) flatMap: 모든 수강 과목을 펼쳐서 (중복 제거+정렬) 리스트로
		System.out.println("\n3) flatMap -- 전체 과목 (중복 제거, 정렬)");
		List<String> allCourses = students.stream()
			.flatMap(s->s.courses.stream())
			.distinct().sorted().toList();

		System.out.println(" "+allCourses);

		// 4) reduce: (a) 모든 점수 총합, (b) 평균 최고 학생(옵셔널)
		System.out.println("\n4) reduce -- 총점/최고 평균 학생");
		// (a) 총점: 모든 학생의 점수들을 하나로 모아 합산
		int totalScore = students.stream()
			.flatMap(s -> s.scores.stream())		// List<List<Integer>> -> Stream<Integer>
			.reduce(0, Integer::sum);		// 식별자 0 + 누적합

		System.out.println(" (a) 전체 점수 총합 = "+ totalScore);
		// (b) 평균 최고 학생: reduce로 최대값 선택
		Optional<Student> best = students.stream()
			.reduce((a, b)-> a.avgScore() >= b.avgScore() ? a:b);
		System.out.println(" (b)최고평균 학생 = "+best.orElse(null));

		// 5) groupingBy: 부서별 평균 점수 (학생들의 '개인평균'을 다시 평균)
		System.out.println("\n5) groupingBy -- 부서별 평균 점수");
		Map<String, Double> avgByDept = students.stream()
			.collect(groupingBy(s-> s.dept, averagingDouble(Student::avgScore)));	// 학생별 평균을 부서별로 다시 평균
		System.out.println(" "+avgByDept);

		// (보너스) groupingBy 응용: 과목별 수강 인원 수
		System.out.println("\n보너스) groupingBy -- 과목별 수강 인원 수");
		Map<String, Long> courseCounts = students.stream()
			.flatMap(s->s.courses.stream())
			.collect(groupingBy(c->c, counting()));
		System.out.println(" "+courseCounts);

	}
}





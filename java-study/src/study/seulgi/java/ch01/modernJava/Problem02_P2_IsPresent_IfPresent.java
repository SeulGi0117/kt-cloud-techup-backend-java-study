package study.seulgi.java.ch01.modernJava;

import java.util.Optional;

public class Problem02_P2_IsPresent_IfPresent {
	static String externalName(boolean asNull) {
		return asNull ? null : "Alice";
	}

	// (안티패턴 시연) isPresent + get 조합
	static String greet_BUGGY(Optional<String> nameOpt) {
		if (nameOpt.isPresent()) {
			return "Hello, " + nameOpt.get();
		}
		// (예상) 이 줄은어떤 문제를 일으키는가?
		// 빈 Optional에서 get() 호출로 NoSuchElementException 발생한다.
		return "Hi, " + nameOpt.get();
	}

	// TODO: ifPresent 또는 ifPresentOrElse로 로그 처리 + 기본값 반환
	static void printGreet_SAFE(Optional<String> nameOpt) {
		// 요구:
		// 1) 값이 있으면 System.out.println("[LOG] greet " + name);
		// 2) 값이 없으면 System.out.println("[LOG] greet null");
		// TODO ...
		nameOpt.ifPresentOrElse(
			n-> System.out.println("[LOG] greet "+n),
		()->System.out.println("[LOG] greet null")
		);
	}

	public static void main(String[] args) {
		System.out.println("== P2: isPresent vs ifPresent ==");

		Optional<String> nameNotNull = Optional.ofNullable(externalName(false));
		Optional<String> nameNull = Optional.ofNullable(externalName(true));
		System.out.println("nameNotNull → " + greet_BUGGY(nameNotNull)); //(예상: nameNotNull → Hello, Alice

		try {
			System.out.println("nameNull → " + greet_BUGGY(nameNull)); // (예상:  호출 중 NoSuchElementException 발생으로 해당 println 완전 출력 실패)
		} catch (Exception e) {
			System.out.println("caught: " + e.getClass().getSimpleName()); // (예상:caught: NoSuchElementException)
		}
		// SAFE 검증
		System.out.println("\n-- SAFE --");
		printGreet_SAFE(nameNotNull); // (예상: [LOG] greet Alice)
		System.out.println("\n-- SAFE null --");
		printGreet_SAFE(nameNull); // (예상: [LOG] greet null)
		printGreet_SAFE(nameNull); // (예상: [LOG] greet null)
	}
}
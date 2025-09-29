package study.seulgi.java.ch01.modernJava;

import java.util.Optional;

public class Problem01_Of_OfNullable {
	// 외부에서 들어온 값(널 가능)
	static String externalEmail(boolean asNull){
		return asNull ? null: "user@example.com";
	}

	//(버그 유도) null일 때 of 사용 -> NullPointerException
	static Optional<String> wrapEmail_BUGGY(String email) {
		// (예상) email이 null이면 어떤 일이 발생?
		return Optional.of(email);
	}

	// TODO: null safe 버전 작성
	// (null 발생 시, null이 아닌 다른 문자열로 변경하여 반환)
	static Optional<String> wrapEmail_SAFE(String email){
		// TODO: email이 null이면 "safe@example.com"으로 대체하여 Optional로 감싸 반환
		// hint: orElse 활용

		String safe = Optional.ofNullable(email).orElse("safe@example.com");
		return Optional.of(safe);
	}

	public static void main(String[] args){
		System.out.println("== P1: of vs ofNullable ==");

		String notNullValue = externalEmail(false);
		String nullValue = externalEmail(true);
		System.out.println("notNullValue = " + notNullValue); // (예상: user@example.com)
		System.out.println("wrap notNullValue (BUGGY) = " +wrapEmail_BUGGY(notNullValue)); // (Optional[user@example.com])

		try {
			System.out.println("wrap nullValue (BUGGY) = " + wrapEmail_BUGGY
				(nullValue)); // (예상: caught: NullPointerException)
		} catch (Exception e) {
			System.out.println("caught: " + e.getClass().getSimpleName()); // (예상:...)
		}
		// TODO: NULL SAFE 버전으로 아래 출력이 기대대로 나오도록 구현
		System.out.println("wrap notNullValue (SAFE) = " + wrapEmail_SAFE(notNullValue)); // (예상: Optional[user@example.com])
		System.out.println("wrap nullValue (SAFE) = " + wrapEmail_SAFE(nullValue)); // (예상: Optional[user@example.com])
	}
}

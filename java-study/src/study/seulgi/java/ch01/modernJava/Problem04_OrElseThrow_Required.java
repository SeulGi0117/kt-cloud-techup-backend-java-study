package study.seulgi.java.ch01.modernJava;

import java.util.Optional;

public class Problem04_OrElseThrow_Required {
	static String externalToken(boolean asNull){
		return asNull ? null: "token-xyz";
	}

	//(버그 유도) 나중에 터질 수 있는 코드
	static String useToken_BUGGY(String token){
		// (예상) token이 null이면 어떤 일이?
		// token이 null이면 NPE 발생. null이 아니면 해당 문자열을 전부 대분자로 반환함.
		return token.toUpperCase();
	}

	// TODO: SAFE - orelseThrow로 null일 경우 IllegalArgumentException 발생,
	// null 이 아닐 경우 해당 문자열 반환
	static String useToken_SAFE(String token){
		Optional.ofNullable(token).orElseThrow(()-> new IllegalArgumentException("token required"));
		return null;
	}

	public static void main() {
		System.out.println("== P4: orElseThrow ==");
		String tokenNotNull = externalToken(false);
		String tokenNull = externalToken(true);

		// System.out.println("tokenNotNull(BUGGY) → " + useToken_BUGGY(tokenNotNull)); // (예상: tokenNotNull(BUGGY) → TOKEN-XYZ)
		// try {
		// 	System.out.println("tokenNull(BUGGY) → " + useToken_BUGGY(tokenNull)); // (예상: NullPointerException 발생)
		// } catch (Exception e) {
		// 	System.out.println("BUGGY caught: " + e.getClass().getSimpleName()); // (예상: BUGGY caught: NullPointerException)
		// }

		// SAFE 검증
		System.out.println("tokenNotNull(SAFE) → " + useToken_SAFE(tokenNotNull)); // (예상: tokenNotNull(SAFE) → null)
		try {
		System.out.println("tokenNull(SAFE) → " + useToken_SAFE(tokenNull)); // (예상: IllegalArgumentException("token required") 발생)
		} catch (Exception e) {
		System.out.println("SAFE caught: " + e.getMessage()); //(예상: SAFE caught: token required)
		}
	}
}

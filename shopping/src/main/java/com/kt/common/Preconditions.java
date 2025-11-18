package com.kt.common;

// 검증작업에서 흠 if문을 숨길수없을까?
// => 구아바 라고 하는 구글에서 쓰는 유틸리티가 있다. 구글 core 라이브러리
public class Preconditions {
	public static void validate(boolean expression, ErrorCode errorCode) {
		// ! 를 붙여서 반전시키는이유 => 개발 방법론. 긍정적인 상황만 보자
		// Happy Path First
		// 긍정적인 상황만 생가하자 -> 패스워드가 이전것과 달라야함 => 해피한 상황
		// 코드 가독성 높음, 유지보수 용이, 테스트 작성 쉬움
		if (!expression) {
			throw new CustomException(errorCode);
		}}
}

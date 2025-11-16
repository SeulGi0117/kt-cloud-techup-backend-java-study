package com.kt.domain.product;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {
	// 객체 생성이 잘됨? 테스트 하고 싶음
	// 제목을 작성하는 2가지 방법. @DisplayName, 메서드명 자체를 한글로 작성
	@Test
	@DisplayName("객체 생성이 잘됨?")
	void 객체_생성_성공() {
		var product = new Product(
			"테스트 상품명",
			100_000L,
			10L
		);
		// 객체가 잘 생성 되었나
		// product의 이름필드의 값이 테스트 상품명인가?
		// self-validating 잘되었는지 확인은 assertThat을 쓴다. - 근데 쳐보면 안나옴. jupiter.core에 있음. 그냥 쓰던건 jupiter.api임.
		// jupiter.api -> assertEquals("테스트 상품명", product.getName());
		assertThat(product.getName()).isEqualTo("테스트 상품명");
		assertThat(product.getPrice()).isEqualTo(100_000L);
		assertThat(product.getStock()).isEqualTo(11L);
		// 실행하면 ?=> 테스트 실패함. expected기대 11개. 근데 but was 10개있다.
	}

	// 하고자 하는거 적고 실패이유. 하고자하는거__실패이유 이런식으로 좀 구분되게 언더바 2개씀
	//@ParameterizedTest 쓰면 파라미터 사용가능.
	@ParameterizedTest
	@NullAndEmptySource // 얘 붙여주면 한번은 null, 한번은 empty로 들어감.
	void 상품_생성_실패__상품명_null_이거나_공백(String name) {

		// 상품명이 공백일때 exception 터지냐?
		// 람다식에서 단일 실행문이면 Return이랑 {}생략가능
		assertThrowsExactly(IllegalArgumentException.class, () ->
			new Product(
				name,
				100_000L,
				10L
			), "상품명은 필수입니다.");  // 이렇게 메세지 써주면됨. 안써줘도 되긴하다.
	}

	// @ParameterizedTest
	// @ValueSource(longs = {-1L, -100L}) // 이건 기본타입이라 null을 못넣는다.
	// void 상품_생성_실패__가격이_음수(Long price){
	// 	assertThrowsExactly(IllegalArgumentException.class, () ->
	// 	new Product(
	// 		"테스트 상품명",
	// 	price,
	// 	10L
	// 	));
	// }

	@Test
	void 상품_생성_실패__가격이_음수(){
		assertThrowsExactly(IllegalArgumentException.class, () ->
			new Product(
				"테스트 상품명",
				-1L,	// 항상 -100_000L ㅇㅈㄹ 말고 그냥 최소값 써라.
				10L	// stock도 가격이랑 똑같다.
			));
	}

	@Test
	void 상품_생성_실패__가격이_null(){
		assertThrowsExactly(IllegalArgumentException.class, () ->
			new Product(
				"테스트 상품명",
				null,
				10L
			));
	}
}
// TODO: test code 작성하고 성공하도록 만들기.
// TODO: TDD -> test 먼저 짜고, product 짜고. 하는거. TDD는 너무 당연한거라 설명을 먼저 안함.
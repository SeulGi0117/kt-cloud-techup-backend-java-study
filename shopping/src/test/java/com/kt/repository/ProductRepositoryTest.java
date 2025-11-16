package com.kt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kt.domain.product.Product;

// 의존성이 생겼으면 SpringBootTest를 작성해주야한다.
@SpringBootTest
@DataJpaTest  // 테스트에 필요한 Bean 들만 쓸수 있게 해주는 어노테이션. 성능에서 이점을 챙겨올수있음
// 근데 얘는 또 쿼리 DSL이 있어야한다.
@Transactional // 얘쓰면 알아서 삭제해줌. 그래서 @AfterEach를 잘 안쓰게됨
class ProductRepositoryTest {
	// 의존성 주입할때 오토와이어드 어노테이션
	@Autowired
	private ProductRepository productRepository;

	//  준비단계 given
	// 상품이 존재해야 이 테스트 가능. 그러면? DB에 상품이 저장되어 있어야한다.
	@BeforeEach
	void setUp(){
		var product = productRepository.save(
			new Product(
				"테스트 상품명",
				100_000L,
				10L
			)
		);
	}
	void 이름으로_상품_검색(){
		// 실행단계 when
		// 검색
		var foundedProduct = productRepository.findByName("테스트 상품명");

		// 검증단계 then
		// 실제로 존재할때는 True, 없으면 false
		org.assertj.core.api.Assertions.assertThat(foundedProduct).isPresent();
	}
}
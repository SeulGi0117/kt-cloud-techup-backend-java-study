package com.kt.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.order.Order;
import com.kt.domain.user.User;

import jakarta.validation.constraints.NotNull;

public interface OrderRepository extends JpaRepository<User, Long> {
	// 1. 네이티브 쿼리로 작성하기
	// 2. jpql로 작성
	// 3. 쿼리메소드로 어찌저찌 작성
	// 4. 조회할떄는 동적쿼리를 작성하게 해줄수 있는 querydsl 사용하자!

	// Java에서 {} 중괄호의 의미는? 정적배열이다.(길이가 제한되어 있는)
	@NotNull
	@EntityGraph(attributePaths = {"ordersProducts", "ordersProducts.product"})
	// join 이랑 똑같이 작동한다
	List<Order> findAllByUserId(Long id);
}

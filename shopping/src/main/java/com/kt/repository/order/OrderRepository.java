package com.kt.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.user.User;

public interface OrderRepository extends JpaRepository<User, Long> {
	// 1. 네이티브 쿼리로 작성하기
	// 2. jpql로 작성
	// 3. 쿼리메소드로 어찌저찌 작성
	// 4. 조회할떄는 동적쿼리를 작성하게 해줄수 있는 querydsl 사용하자!
}

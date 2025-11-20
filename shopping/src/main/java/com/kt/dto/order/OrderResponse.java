package com.kt.dto.order;

import java.time.LocalDateTime;

import com.kt.domain.order.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;

public interface OrderResponse {
	// 3가지의 방법으로 querydsl 결과를 dto에 매핑할 수 있다
	// 1 클래스 프로젝션(Search라는 클래스가 Q클래스로 만들어지면 new로 생성자에 맞게 만드는 방법) (v)
	// 2. 어노테이션 프로젝션 (@QueryProjection) (v)
	// 3. 그냥 POJO로 직접 매핌
	record Search(
		Long id,
		String receiverName,
		String productName,
		Long quantity,
		Long totalPrice,
		OrderStatus status,
		LocalDateTime createdAt
	) {
		// 보통 record 로 할때는 1, 2 방법을 많이 쓴다.
		// 이렇게 어노테이션 프로젝션을 달아줘야 빈으로 생성됨
		@QueryProjection
		public Search {

		}
	}
}

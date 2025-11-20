package com.kt.controller.order;

import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.ApiResult;
import com.kt.common.Paging;
import com.kt.dto.order.OrderResponse;
import com.kt.repository.order.OrderRepository;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor

// 관리자가 주문 관리하는 페이지
public class AdminOrderController {
	// 이미 repository 구현체에서 구현(쿼리)를 다 끝내놔서 여기서 서비스에서 하는게 없음
	// 1. 리포지토리 주입 바로 받아서 하럭냐 -> 싱크홀 안티패턴 (v)
	// 2. 그래도~ 서비스를 통해야한다. -> 얘를 하게 되면 관리해야하는게 많아져서 번거로움
	private final OrderRepository orderRepository;
	@GetMapping
	public ApiResult<PageImpl<OrderResponse.Search>> search(
		// 상품명으로 검색할수 있는 keyword
		@RequestParam(required = false) String keyword,
		@Parameter(hidden=true) Paging paging
	) {
		return ApiResult.ok(orderRepository.search(keyword, paging.toPageable()));
	}
}

package com.kt.controller.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.ApiResult;
import com.kt.dto.order.OrderRequest;
import com.kt.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

	// 주문생성
	// validation 동작하도록 알려주는 어노테이션
	@PostMapping
	public ApiResult<Void> create(@RequestBody @Valid OrderRequest.Create request){
		orderService.create(
			request.userId(),
			request.productid(),
			request.receiverName(),
			request.receiverAddress(),
			request.receiverMobile(),
			request.quantity()
		);
		return ApiResult.ok();
	}
}

// todo: 주문생성, 주문상태변경, 주문생성완료 되면 재고차감, 배송받는사람 정보 변경, 주문취소
//  거의 생성의 의미만 있을듯?

package com.kt.controller.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

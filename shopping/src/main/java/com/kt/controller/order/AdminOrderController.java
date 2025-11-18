package com.kt.controller.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.Paging;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
	@GetMapping
	public void search(
		@RequestParam(required = false) String keyword,
		@Parameter(hidden=true) Paging paging
	)
}

package com.kt.controller.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.commone.ApiResult;
import com.kt.dto.product.ProductRequest;
import com.kt.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor  // 생성자 주입
public class ProductController {
	private final ProductService productService;  // 생성자 주입

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResult<Void> create(@RequestBody @Valid ProductRequest.Create request) {
		productService.create(
			request.getName(),
			request.getPrice(),
			request.getQuantity()
		);

		return ApiResult.ok();
	}

	@PutMapping("/{id}")
	public ApiResult<Void> update(@PathVariable Long id, @RequestBody @Valid ProductRequest.Update request) {
		productService.update(
			id,
			request.getName(),
			request.getPrice(),
			request.getQuantity()
		);
		return ApiResult.ok();
	}

	// soldOut 처리 url
	@PatchMapping("/{id}/dole-out")
	public ApiResult<Void> soldOut(@PathVariable Long id) {
		productService.soldOut(id);

		return ApiResult.ok();
	}

	// activate 처리 url
	@PutMapping("/{id}/activate")
	public ApiResult<Void> activate(@PathVariable Long id) {
		productService.activate(id);

		return ApiResult.ok();
	}

	// inActivate 처리 url
	@PutMapping("/{id}/in-activate")
	public ApiResult<Void> inActivate(@PathVariable Long id) {
		productService.inActivate(id);

		return ApiResult.ok();
	}

	// 우리는 soft delete 하는 쪽이라 사실상 삭제가 아니라 수정이긴한데, 외부 입장에서는 삭제긴함.
	@DeleteMapping("/{id}")
	public ApiResult<Void> remove(@PathVariable Long id) {
		productService.delete(id);

		return ApiResult.ok();
	}
}

package com.kt.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductRequest {
	@Getter
	@AllArgsConstructor
	public static class Create {
		@NotBlank
		private String name;
		@NotNull
		private Long price;
		@NotNull
		private Long quantity;
	}

	@Getter
	@AllArgsConstructor
	public static class Update {
		@NotBlank
		private String name;
		@NotNull
		private Long price;
		@NotNull
		private Long quantity;

	}
}

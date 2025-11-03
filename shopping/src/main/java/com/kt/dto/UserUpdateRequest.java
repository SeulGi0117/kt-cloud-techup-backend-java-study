package com.kt.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
	@NotBlank
	String name,
	@NotBlank
	String mobile,
	@NotBlank
	String email
) {

}

package com.kt.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
	@NotBlank
	String loginId,
	@NotBlank
	@NotNull
	String password) {

}

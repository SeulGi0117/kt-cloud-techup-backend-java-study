package com.kt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// 변경이라 불변성을 보장받을 수 없기때문에
// 레코드를 쓰면 코드가 엄청 줄고, 게터랑 이런거 다 제공 불변성 쉽게 할수있다.
public record UserUpdatePasswordRequest(
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^])[A-Za-z\\d!@#$%^]{8,}$")
	String oldPassword,

	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^])[A-Za-z\\d!@#$%^]{8,}$")
	String newPassword
){

}

package com.kt.dto.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

// sealed interface 방법도 있는데 뭐 계속 붙여줘야해서 안쓰는게 편할듯
public interface UserResponse {

	record Search(
		Long id,
		String name,
		LocalDateTime createdAt
	){}
	record Detail(
		Long id,
		String name,
		String email
	){}
}

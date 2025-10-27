package com.kt.dto;

import java.time.LocalDate;

// loginID, apssword, name, birthday(YYYY-mm-dd) 정보 받기
public record UserCreateRequest(
	String loginId,
	String password,
	String name,
	LocalDate birthday
) {}


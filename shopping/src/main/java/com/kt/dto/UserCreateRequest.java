package com.kt.dto;

import java.time.LocalDate;

// loginID, apssword, name, birthday(YYYY-mm-dd) 정보 받기
// DB 에 저장해야할 메서드를 받는것
public record UserCreateRequest(
	String loginId,
	String password,
	String name,
	String email,
	String mobile,
	String gender,
	LocalDate birthday
) {}


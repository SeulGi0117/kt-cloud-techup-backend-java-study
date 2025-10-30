package com.kt.dto;

import java.time.LocalDate;
import com.kt.domain.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// bean validation 이라는 기능을 통해서 유효성 검사
// loginID, apssword, name, birthday(YYYY-mm-dd) 정보 받기
// DB 에 저장해야할 메서드를 받는것
public record UserCreateRequest(
	@NotBlank	// null 이거나 공백이면 안됨. 이런 유효성 검사 어노테이션 " " 이런 띄어쓰기 공백도 막아줌
	String loginId,
	@NotBlank // password도 똑같이 적용해준다
	// 비번 유효성 조건: 최소 8자 이상 대문자 소문자 숫자 특수문자 포함 이런거. -> 정규식으로 검증
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^])[A-Za-z\\d!@#$%^]{8,}$") // 어차피 이런 정규식은 걍 ai 시켜서 만들어달라하면된다 ㅇㅇ
	String password,
	@NotBlank
	String name,
	@NotBlank
	// 얘도 ai한테 이메일 정규식 만들어 달라하면 된다
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	String email,
	@NotBlank
	// 폰 번도 하이픈 있는거? 없는ㄷ거? 하이픈 있는거로 강제하자
	@Pattern(regexp = "^\\d{1,2}-\\d{3,4}-\\d{4}$")
	String mobile,
	@NotNull		// " ", "남자" 객체 이넘은 애초에 이런거 호환이 안됨. 그래서 null인 경우만 방어한다.
	Gender gender,
	@NotNull 		//20020117 이렇게 보내면 오류나서 2002-01-17 이렇게 들어오게 함. 그래서 notnull 붙임
	LocalDate birthday
) {}


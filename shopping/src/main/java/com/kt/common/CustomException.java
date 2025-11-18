package com.kt.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	// 에러의 코드들을 사용하기
	private final ErrorCode errorCode;

	public CustomException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}

package com.kt.common;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

// Error를 공통된 응답으로 처리하는 방
// Spring에서는 @RestControllerAdvice라는 어노테이션  방법이 있다.

@Hidden // Swagger 를 쓰려면 히든을 붙여줘야한다.
@RestControllerAdvice
public class ApiAdvice {
	// 어떤 예외를 처리할 것인지 정의
	// MethodArgumentNotValidException 이 익셉션을 처리하도록
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	// 500 에러를 하나로 처리할떄
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse.ErrorData> internalServererror(Exception e){
		// 서버에러입니다. 이렇게 해주고 싶음
		// 서버에러면 어디서 잘못된건지 알아야하니까 로그찍기
		// TODO: 500에러 뜨면 슬랙이나 문자로 받거나 하는거 해보기
		e.printStackTrace();	// <- 어떤 에러가 뜬건지 알아 볼 떄

		return ErrorResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다 - 백엔드에 바로 문의 바랍니다.");
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse.ErrorData> customException(CustomException e){
		return ErrorResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
	}

	// Exception 핸들러 안에 어떤 Exception을 처리할건지 넣어주면 된다
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse.ErrorData> methodArgumentNotValidException(MethodArgumentNotValidException e){
		e.printStackTrace();	// <- 어떤 에러가 뜬건지 알아 볼 떄

		var details = Arrays.toString(e.getDetailMessageArguments());
		var message = details.split(",", 2)[1].replace("]", "").trim();
		return ErrorResponse.error(HttpStatus.BAD_REQUEST, message);
	}
}

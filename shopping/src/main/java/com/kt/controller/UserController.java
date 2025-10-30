package com.kt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.dto.UserCreateRequest;
import com.kt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	// userservice를  di받아야함
	// di받는 방식이 생성자 주입 씀 -> 재할당을 금지함 불변유지를 위해 쓴다

	private final UserService userService;

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	// loginID, apssword, name, birthday 정보 받기
	// json으로 하나가 들어온다. DTO랑 json 객체를 맵핑해주는 친구가 하나있다.
	// json 형태의 body에 담겨서 post 요청으로 /users 로 들어오면
	// @RequestBody를 보고 jacksonobjectMapper가 동작해서 json을 읽어서 dto로 변환.

	public void create(@RequestBody UserCreateRequest request){
		System.out.println(request.toString());
		// jackson object mapper -> json to dto를 매핑

	}
}

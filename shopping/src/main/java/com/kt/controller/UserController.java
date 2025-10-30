package com.kt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.dto.UserCreateRequest;
import com.kt.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name= "유저", description = "유저관련 API")
@RestController
@RequiredArgsConstructor
public class UserController {
	// userservice를  di받아야함
	// di받는 방식이 생성자 주입 씀 -> 재할당을 금지함 불변유지를 위해 쓴다

	private final UserService userService;

	// API 문서화는 크게 2가지 방식이 존재
	// 1.Swagger
	// 장점: UI가 이쁘다. 어토테이션 기반이라 작성이 쉽다.
	// 단점: 프로덕션 코드에 Swagger 관련 어노테이션이 존재하기 때문에 controller의 SRP를 위반하게 된다.(controller는 요청 및 응답에만 전념하는 것이 SRP이다. Swagger를 쓰면 문서화까지 시키는거라 SRP위반됨.)
	// 코드가 더러워지고 길어지고 그래서 유지보수가 힘들다.
	// 2.RestDocs
	// 1번이랑 정반대
	// 장점: 프로젝션 코드에 침범이 없다.신뢰할 수 있다(무조건테스트를 거치니까)
	// 단점: UI가 안이쁘다, 테스트코드 기반이라 문서작성한느데 시간이 굉장히 오래걸린다. 테스트안하면 문서화가 안됨. 강제적으로 테스트 하게됨
	@ApiResponses(value = {
		@ApiResponse(responseCode = "400", description = "유효성 검사 실패"),
		@ApiResponse(responseCode = "500", description = "서버 에러 - 백엔드에 바로 문의 바랍니다."),
	})

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

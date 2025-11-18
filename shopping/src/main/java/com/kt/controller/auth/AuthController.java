package com.kt.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.ApiResult;
import com.kt.dto.auth.LoginRequest;
import com.kt.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")	// GET mapping 쓰면 URL 다 노출된다. POST를 써야 Body에 넘어간다
	public void login(@RequestBody @Valid LoginRequest request){
		var token = authService.login((request.loginId()), request.password());

		return ApiResult.ok(new LoginResponse(Pair.getFirst(), Pair.getSecond()));
		return ApiResult.ok(token);


	}
	// 인증관련 컨트롤러를 구현
	// 인증방식 크게 3가지가 존재함
	// 1. 세션기반 인증 -> 서버쪽에 작은 공간에 사용자 정보를 저장 - 만료시간이 존재
	// 서버에서 관리하기 떄문에 보안성이 좋다.
	// A서버에서 인가를 해줌. 세션에 저장하고 있음. 라운드 로빈으로 B서버 세션에는 인가된 정보가 없다.
	// 해결책: 세션클러스터링, 스티키세션 -> redis등 외부 저장소를 통해서 단일 세션을 구현, 세션이 A서버에서 생성되었다면 A서버로 트래픽 고정
	// redis를 위한 redis. 지금현재 redis 부하가 심하니까 또 이 Redis를 위한 Redis를 만든다. -> 서버유지비용 레전드로됨

	// 2. 토큰기반 인증(JWT) -> 사용자가 토큰을 가지고 있다가, 요청할때마다 서버에 같이 넘겨줌
	// 서버 입장에서는 신뢰성x, 단점: 매번 검사를 해야함, 장점: 서버에서 관리하지 않아서 부하가 적음, 분산환경에 유리

	// 3. OAuth2.0 기반 인증
	// 내 서버가 아니라 남한테 맡기는 방식(구글, 카카오, 네이버, 깃헙, 페북)
	// 장점: 사용자 편하려고 만든게 아니라, 서버 개발자들 편하려고 쓰는거임
	// 왜 편함? => 개인정보를 취급하지 않아도 돼서, 인가 작업을 내가 안해도 돼서. 필터 이런거 안만들어도 되잖슴

}

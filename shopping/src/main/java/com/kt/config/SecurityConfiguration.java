package com.kt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// @EnableWebFluxSecurity // 비동기
@EnableMethodSecurity
public class SecurityConfiguration {
	// permit all 이 뭐 누구에게 공개할거냐. path는 뭐 헬스체크, 테스트 이런거.
	private static final String[] GET_PERMIT_ALL = {"api/health/**"};	// 헬스체크에 관련해서는 인증인가 상관 노라는뜻
	private static final String[] POST_PERMIT_ALL = {"api/v1/public/**"};
	private static final String[] PATCH_PERMIT_ALL = {"api/v1/public/**"};
	private static final String[] PUT_PERMIT_ALL = {"api/v1/public/**"};
	private static final String[] DELETE_PERMIT_ALL = {"api/v1/public/**"};

	// 패스워드 저장할거면 암호화해. <= 이걸 강제화 하고 있다.
	// bcrypt 단방향해시암호화
	// => 평문을 5번 해싱해서 랜덤한 값을 저장함 -> 비교할때는 5번 해싱해서 같은지를 비교한다.
	// Bean으로 등록해줘야한다.
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// 얘도 기본이라 이거 등록해줘야한다. 그냥 기본임.
	// todo: 얘 뭐하는건지 공부해보기
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.sessionManagement(	// 얘는 람다로만 되어 있음
			session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
			.authorizeHttpRequests(	// permitAll() 뜻 => 다 공개해도 상관없어.
				request -> {
					request.requestMatchers(HttpMethod.GET, GET_PERMIT_ALL).permitAll();
					request.requestMatchers(HttpMethod.POST, POST_PERMIT_ALL).permitAll();
					request.requestMatchers(HttpMethod.PATCH, PATCH_PERMIT_ALL).permitAll();
					request.requestMatchers(HttpMethod.PUT, PUT_PERMIT_ALL).permitAll();
					request.requestMatchers(HttpMethod.DELETE, DELETE_PERMIT_ALL).permitAll();
				}
			)
			.authorizeHttpRequests(request -> request.anyRequest().authenticated())
			.csrf(AbstractHttpConfigurer)
	}
}

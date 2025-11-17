package com.kt.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtService {
	// Key는 우리가 설정한 어떤 임의의 값을 통해서 Key를 생성함
	private final JwtProperties jwtProperties;

	public void issue(Long id) {
		// issue 발급하다라는뜻
		// id 값은 jwt의 식별자 같은 개념 -> User의 Id값을 넎는편
		// claims => jwt 안에 들어갈 정보를 Map 형태로 넣는데 id,1 이렇게 넣는다.
		var token = Jwts.builder()
			.subject("kt-cloud-shopping")
			.issuer("sg")    // 누가 발급해줌?
			.issuedAt(new Date()) // 언제 발행이 됐냐
			.id(id.toString())
			.expiration(new Date())    // 언제 만료 될건가?, 일단 바로 만료되게, new Date
			// 우리서버에서 발급된거라는 싸인을 남겨줌 -> 근데 보안취약점땜에 Key를 구현해서 넣어줘야 될것같음
			.signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
			.compact();  // 호출하게 되면 토큰으로(String)으로 담기게 된다

		return token;
	}

	public String createToken(String loginId) {
		return null;
	}

}

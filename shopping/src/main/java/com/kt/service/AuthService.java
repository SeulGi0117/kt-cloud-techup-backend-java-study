package com.kt.service;

import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.CustomException;
import com.kt.common.ErrorCode;
import com.kt.common.Preconditions;
import com.kt.repository.user.UserRepository;
import com.kt.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public Pair<String, String> login(String loginId, String password) {
		// loginId로 실제로 유저가 존재한느지 리포지토리에서 찾아와야한다
		var user = userRepository.findByLoginId(loginId)
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		// 비밀번호가 일치하는지 검증
		// user.getPassword();	// Bcrypt로 암호화된 정보 -> 단방향 해시암호화(5번 해시 알고리즘 돌림). 복호화 불가
		// => 맞는지 볼때는, 요청들어온 password를 또 해시알고리즘 5번 돌려서 맞는지 비교
		// boolean matches(CharSequence rawPassword, String encodedPassword);
		// raw가 사용자가 넣은거, encoded는 우리서버에 해쉬된거
		Preconditions.validate(!passwordEncoder.matches(password, user.getPassword()), ErrorCode.FAIL_LOGIN);

		// 로그인 성공처리 -> JWT 토큰을 발급 => JWT Service 구현
		// 헤더에 넣어서 줄수도 있고, body에 넣어서 줄수도 있다(보통선호), 쿠키에 넣어서 줄수도 있다. -> 나머지는 프론트에서 파싱에서 꺼내서 못씀.
		var accessToken = jwtService.issue(user.getId(), jwtService.getAccessExpiration());
		var refreshToken = jwtService.issue(user.getId(), jwtService.getRefreshExpiration());

		return Pair.of(accessToken, refreshToken);

	return	jwtService.issue(user.getId());
	}

	public void logout() {

	}

}

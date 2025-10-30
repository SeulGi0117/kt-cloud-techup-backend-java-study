package com.kt.service;

import java.time.LocalDateTime;

import com.kt.dto.UserCreateRequest;
import com.kt.domain.User;
import com.kt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService{
	private final UserRepository userRepository;  // ← 생성자 주입(Lombok)

	@Transactional
	public void create(UserCreateRequest request){
				System.out.println(request.toString());
		var newUser = new User(
			userRepository.selectMaxId() + 1,
			request.loginId(),
			request.password(),
			request.name(),
			request.email(),
			request.mobile(),
			request.gender(),
			request.birthday(),
			LocalDateTime.now(),
			LocalDateTime.now()
		);
		userRepository.save(newUser);
	}

	// todo: id 중복 검사 만들기
}
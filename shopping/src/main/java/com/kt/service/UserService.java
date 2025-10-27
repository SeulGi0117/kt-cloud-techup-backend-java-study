package com.kt.service;

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
			request.loginId(),
			request.password(),
			request.name(),
			request.birthday()
		);

		userRepository.save(newUser);
	}
}
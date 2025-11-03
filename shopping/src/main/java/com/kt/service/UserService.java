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

		// repository로 넘길거임
	}

	// 기본형은 null 절대 안됨.
	public boolean isDuplicateLoginId(String loginId){
		return userRepository.existsByLoginId(loginId);
	}

	public void changePassword(int id, String oldPassword, String password){
		// 서비스 입장에서는 id 값이 외부에서 들어오느 값이기 때문에 int로 받음
		// 실제로 db에 유저가 존재하냐?
		// 유저가 존해하면 업데이트 : 존재하지 않으면 없단느 예외처리
		// 1234 -> 1234 로 바꾸면 예외처리 이런거

		if(!userRepository.existsById(id)){
			throw new IllegalArgumentException("존재하지 않는 회원입니다.");
		}
		if(oldPassword.equals(password)){
			throw new IllegalArgumentException("기본 비밀번호와 동일한 비밀번호는 사용할 수 없습니다.");
		}
		userRepository.updatePassword(id, password);
	}
}
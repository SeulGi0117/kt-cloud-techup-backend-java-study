package com.kt.service;

import java.time.LocalDateTime;

import com.kt.common.CustomException;
import com.kt.common.ErrorCode;
import com.kt.common.Preconditions;
import com.kt.dto.user.UserCreateRequest;
import com.kt.domain.user.User;
// import com.kt.repository.UserJdbcRepository;
import com.kt.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 구현체가 하나 이상 필요로 해야 인터페이스가 의미가 있다.
// 인터페이스 : 구현체 1:1로 다 나눠야 하나. -> 실무에서도 많이 고민한다 -> 한개일떄는 굳이? 2개 이상이면 나누기
// 관례를 지키려고(원래는 인터페이스 만드는게 맞음) 추상화를 굳이 하는것을 관습적 추상화라고 한다.
// 인터페이스를 굳이 나눴을때 불편한점 -> 유지보수 힘들다(관리포인트 늘어나서)

@RequiredArgsConstructor
@Service
@Transactional // 실수 많이 할것같으면 그냥 Class 레벨에다가 달아주면 된다. ReadOnly = true는 쓰기가 불가능. 상태 변경해도 더티체킹 동작x.
// 이런 경우에는 밑에다가 @Transactional 붙여주면 된다.
public class UserService {
	// private final UserJdbcRepository userJdbcRepository;  // ← 생성자 주입(Lombok) 추억속으로 ... ㅃ2
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// 트랜잭션 처리해줘. 어노테이션 달아야함. Entity에는 자카르타 어노테이션을 썼다.(엔티티 자체가 spring bean이 아니라서)
	// 근데 서비스는? spring bean으로 되어있음. transactional 은 spring 거로 어노테이션 달아야한다.

	// PSA - Portable Service Abstraction
	// 환경설정을 살짝 바꿔서 일관된 서비스를 제공하는 것
	@Transactional
	public void create(UserCreateRequest request) {
		var newUser = new User(
			request.loginId(),
			passwordEncoder.encode(request.password()),
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
	public boolean isDuplicateLoginId(String loginId) {
		return userRepository.existsByLoginId(loginId);
	}

	public void changePassword(Long id, String oldPassword, String password) throws Throwable {
		var user = userRepository.findByIdOrThrow(id, ErrorCode.NOT_FOUND_USER);

		// 서비스 입장에서는 id 값이 외부에서 들어오느 값이기 때문에 int로 받음
		// 실제로 db에 유저가 존재하냐?
		// 유저가 존해하면 업데이트 : 존재하지 않으면 없단느 예외처리
		// 1234 -> 1234 로 바꾸면 예외처리 이런거

		// 유저를 조회해서 비밀번호가 조회한 비번과 새로운 비번이 같은지?
		// 패스워드가 이전건과 달라야한다 => 해피한 상황 // 패스워드가 같으면 안되는데 => 해피하지 않은 상황
		Preconditions.validate(!user.getPassword().equals(oldPassword), ErrorCode.DOES_NOT_MATCH_OLD_PASSWORD);
		Preconditions.validate(!oldPassword.equals(password), ErrorCode.CAN_NOT_ALLOWED_SAME_PASSWORD);

		user.changePassword(password);
		// userRepository.save(user); 이렇게 해줘도 된다.
	}

	// Pageable 인터페이스가 존재한다. springframework의 data.domain 얘의 pageable를 꼭 가져와서 사용해야함!
	public Page<User> search(Pageable pageable, String keyword) {
		return userRepository.findAllByNameContationg(keyword, pageable);
	}

	public User detail(Long id) {
		return userRepository.findByIdOrThrow(id, ErrorCode.NOT_FOUND_USER);
	}

	public void update(Long id, String name, String email, String mobile) {
		// user 존재 검증
		var user = userRepository.findByIdOrThrow(id, ErrorCode.NOT_FOUND_USER);
		user.update(name, email, mobile);
	}

	public void delete(Long id) {
		// 삭제 개념 - soft, hard

		// [Hard Delete]1번째 방식. 별 이유 없으면 쿼리 한번 발생하는방법을 쓴다.
		userRepository.deleteById(id);

		//2번째 방식. 삭제 상태로 만드러주는 방법. 우선 영속상태로 만듦. 쿼리 2번나감.
		// 뭐 주문내역이나 다른거 다 삭제하는 처리 해야하면 이렇게 쓰면 됨
		// var user = userRepository.findById(id)
		// 	.orElseThrow(() -> new IllegalArgumentException(("존재하지 않는 회원입니다")));
		// userRepository.delete(user);
	}
}
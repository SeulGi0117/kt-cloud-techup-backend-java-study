package com.kt.repository.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.commone.CustomException;
import com.kt.commone.ErrorCode;
import com.kt.domain.user.User;

// <T, ID>
// T: Entity 클래스=> User
// ID: Entity 클래스의 PK 타입 => Long
public interface UserRepository extends JpaRepository<User, Long> {
	// JPA에서는 쿼리를 작성하는 3가지 방법이 존재
	// 1. 네이티브 쿼리 작성 => 얘 쓸거면 JPA 쓸필요가 없음. 걍 JDBC 만들어 둔거 쓰면됨
	// 2. JPQL 작성 -> 네이티브 쿼리랑 같은데 Entity 기반으로 한다 (2번째로 많이씀) - 너무길어진 메소드 이름을 그냥쿼리작성해서 숨김
	// 3. querymethod 작성 -> 메서드 이름을 쿼리처럼 작성 (얘를 제임 많이씀) - 이름이 길어지면 상당히 이상해보임
	// 뭐가 조회할때 찾는다: findByxx. 존재하냐? existsByxx, 삭제: deleteByxx

	Boolean existsByLoginId(String loginId);

	Optional<User> findByLoginId(String loginId);

	Page<User> findAllByNameContationg(String name, Pageable pageable);

	// todo: 25.11.07 실습코드 따라 작성해야함
	default User findByIdOrThrow(Long id, ErrorCode errorCode){
		return findById(id).orElseThrow(() -> new CustomException(errorCode));
	}

	/*
	// 2. JPQL로 작성하는 방법
	@Query("""
		SELECT exists(SELECT u FROM User u WHERE s.loginId =?1)
		""")
	Boolean existsByLoginIdJPQL(String loginId);

	// Native 쿼리 작성법
	@Query("""

	""")
	Boolean existsByLoginIdJPQL(String loginId);
	*/
}


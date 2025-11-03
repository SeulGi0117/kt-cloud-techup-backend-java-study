package com.kt.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kt.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;

	public void save(User user) {
		// 서비스에서 dto를 도메인으로 바꾼다음 전달.
		var sql = """
			INSERT INTO USER (
			id,
			loginId,
			password,
			name,
			birthday,
			mobile,
			email,
			gender,
			createdAt
			updateAt
			) VALUES (?, ?, ?, ?,?,?,?,?,?,?)
		""";

		jdbcTemplate.update(
			sql,
			user.getId(),
			user.getLoginId(),
			user.getPassword(),
			user.getName(),
			user.getBirthday(),
			user.getMobile(),
			user.getEmail(),
			user.getGender(),
			user.getCreatedAt(),
			user.getUpdateAt()
		);
		System.out.println("save user: " + user.toString());// 백엔드에서 도메인이란? 주소체계가 아니라 도메인(비지니스 모델)

	}

	public Long selectMaxId() {
		var sql = "SELECT MAX(id) FROM USER;";
		var maxId = jdbcTemplate.queryForObject(sql, Long.class);
		return maxId == null ? 0L : maxId;
	}
	// 크게 세가지 젇오 아이디 중복 체크 방법
	// 1 .count해서 0보다 큰지 체크 -> 강사님은 별로 좋아보이진 않음
	// 	=> db에 만야 ㄱ유저가 3000만명 -> 1번 중복체크 할떄마다 3천개의 데이터를 모두 살펴봐야한다.(full-scan)
	// 	db에 만약 동시에 여러 요청이 들어온다면 중복체크 후 저장 사이에 끼어들수도 있음
	//
	// 2. unique 제약조건 걸어서 예외 처리 -> 별로 좋아보이진 않음
	// => 유니크 키 에러(DataViolation Exception) 별로 좋아보이진 않음
	// 3. exists로 존재 여부 체크 -> boolean으로 값 존재 여부를 바로 알 수 있다. 있으면 T 없으면 F

	public boolean existsByLoginId(String loginId) {
		var sql = "SELECT EXISTS(SELECT id FROM MEMBER WHERE loginId = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, loginId));
		// return jdbcTemplate.queryForObject(sql, Boolean.class, loginId);
	}
	public void updatePassword(int id, String password){
		// UPDATE {table} SET {column} = {value}, {cplumn} = {value} WHERE {condition}
		// var sql = "UPDATE MEMBER SET password = ?";	// 모든세트 비번 다바꾸게됨

		var sql = "UPDATE MEMBER SET password = ? WHERE id =?";
		jdbcTemplate.update(sql, password, id);
	}

	public boolean existsById(int id) {
		var sql = "SELECT EXISTS(SELECT id FROM MEMBER WHERE loginId = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
	}
}

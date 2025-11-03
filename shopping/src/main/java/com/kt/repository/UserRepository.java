package com.kt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kt.domain.Gender;
import com.kt.domain.User;
import com.kt.dto.CustomPage;

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

	public void updatePassword(Long id, String password) {
		// SRP 관점에서 => 비번을 잘 바꿀 책임.
		// UPDATE {table} SET {column} = {value}, {cplumn} = {value} WHERE {condition}
		// var sql = "UPDATE MEMBER SET password = ?";	// 모든세트 비번 다바꾸게됨

		var sql = "UPDATE MEMBER SET password = ? WHERE id =?";
		jdbcTemplate.update(sql, password, id);
	}

	public boolean existsById(Long id) {
		var sql = "SELECT EXISTS(SELECT id FROM MEMBER WHERE loginId = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
	}

	// id 값으로 유저를 DB에서 조회해서 User 객체를 반환하는 메서드가 필요하다
	public Optional<User> selectById(Long id) {
		var sql = "SELECT * FROM MEMBER WHERE id = ?";
		// jdb ResultSet 객체로 반환을 함
		// sql에서 *은 모든 컬럼
		var list = jdbcTemplate.query(sql, rowMapper(), id);
		System.out.println(list);
		return list.stream().findFirst();
	}

	public CustomPage selectAll(int page, int size) {

		// paging의 구조
		// [백엔드입장에서 필요한것]
		// 한 화면에 몇개 보여줄것인가?	=> limit(db에서 limit이라고함)
		// 내가 몇번째 페이지를 보고 있는가?	=> offset (몇개를 건너 뛸것인가?)
		// 보고 있는 페이지 -1 *limit. 프론트가-1 하는경우도 있고 백엔드가 하는 경우도 있고
		// full-scan을 하기 때문에 성능이슈가 생긴다 -> cursor 기반으로 바꾸면 성능 ok. 하지만우린 여기까진 안할거
		var sql = "SELECT * FROM MEMBER LIMIT ? OFFSET ?";

		var users = jdbcTemplate.query(sql, rowMapper(), page, size);

		var countSql = "SELECT COUNT(*) FROM MEMBER";
		var totalElements = jdbcTemplate.queryForObject(countSql, Long.class);
		var pages =(int)Math.ceil((double) totalElements / size);

		return new CustomPage(
			users,
			size,
			page,
			pages,
			totalElements
			);
	}

	private RowMapper<User> rowMapper() {
		return (rs, rowNum) -> mapToUser(rs);
		// () -> {return A} 람다는 단일 실행문이면 {}와 return 생략이 가능하다.
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		return new User(
			rs.getLong("id"),
			rs.getString("loginId"),
			rs.getString("password"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("mobile"),
			rs.getObject("gender", Gender.class),
			rs.getObject("birthday", LocalDateTime.class),
			rs.getObject("createdAt", LocalDateTime.class),
			rs.getObject("updateAt", LocalDateTime.class),
			);
	}
}

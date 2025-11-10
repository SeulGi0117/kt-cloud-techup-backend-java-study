package com.kt.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kt.commone.BaseEntity;
import com.kt.domain.order.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 1. domain과 entity를 분리해야 한다. - 이런파. 원래 해야되긴 하는데 계속 하다보면 성능저하이슈나 일이 많아짐
// 2. 굳? 그냥 가이 쓰지 뭐 - 분리 안한다 파
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 이거 Entity야~! 하고 알랴줘야함. 어노테이션을 붙여 알려줄수있다.
@Entity()
@Table(name = "MEMBER")
// entity가 spring bean은 아니다. POJO같이 쓰인다. new user 로 사용해야한다.
public class User extends BaseEntity {
	// Persistent entity 'User' should have primary key
	@Id  // Spring이 아닌 jakart Id 어노테이션을 사용해야한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String mobile;
	// enum 다루는 방식 2개
	// ordinal: enum의 순서를 DB에 저장 .=> [절대 사용하지 마셈]
	// String: enum의 값 DB에 저장.
	@Enumerated(EnumType.STRING) // 기본값이 ordinal이라서 String으로 바꾸어주어야한다.
	private Gender gender;
	private LocalDate birthday;

	// 매핑테이블
	// 1 : 1 뭐 이런식으로 만들어줌
	// 1:2
	@OneToMany(mappedBy = "user") // orders에 맡길거임
	private List<Order> orders = new ArrayList<>();

	public User(String loginId, String password, String name, String email, String mobile, Gender gender,
		LocalDate birthday, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.birthday = birthday;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public void changePassword(String password) {
		this.password = password;
	}

	public void update(String name, String email, String mobile) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
}

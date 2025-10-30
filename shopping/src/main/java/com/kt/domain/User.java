package com.kt.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.relational.core.dialect.LockClause;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long id;
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String mobile;
	private Gender gender;
	private LocalDate birthday;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	public User(long id, String loginId, String password, String name, String email, String mobile, String gender, LocalDate birthday, LocalDateTime now, LocalDateTime now1) {
	}
}

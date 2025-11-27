package com.kt.common;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// JPA에서 부모객체로서 인식할수있는 작업처리를 해야한다. MappedSuperclass
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}

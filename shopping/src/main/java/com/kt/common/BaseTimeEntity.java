package com.kt.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity extends BaseAuditEntity{

	// 자동으로 넣을 수 있는 방법 JPA Auditing이 있다.
	// 1. auditing을 활성화 하는 방법 - Application 클래스에 넣거나 config 설정파일로 넣거나
	@CreatedDate
	protected LocalDateTime createdAt;
	@LastModifiedBy
	protected LocalDateTime updatedAt;
}

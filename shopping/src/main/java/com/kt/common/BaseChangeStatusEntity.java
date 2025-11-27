package com.kt.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseChangeStatusEntity extends BaseEntity {
	@LastModifiedBy
	protected LocalDateTime changeStatusAt = LocalDateTime.now();
}
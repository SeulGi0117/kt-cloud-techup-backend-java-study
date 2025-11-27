package com.kt.common;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity {
	// 시간도 기록 했으니까
	// 만든사람, 수정한사람 달아주고 싶다

	// 그래서 누가 하는건지 jpa한테 알려줘야함
	// AuditorAware 라는게 있다. 이거 값 세팅한 사람을 아래의 값에 각각 들어가게 된다.
	// 만약 수정했다면, updateBy만 들어가게 되거나, 처음 만들면 createdBy랑 들어가게 된다. userid 가

	@CreatedBy
	protected Long createdBy;
	// 수정한 사람(작성자)를 특별히 관리해야한다? 그러면 String으로 이름을 참고용으로 박아두기도 함

	@LastModifiedBy
	protected Long updatedBy;
}

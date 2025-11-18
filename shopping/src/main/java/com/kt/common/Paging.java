package com.kt.common;

import org.springframework.data.domain.PageRequest;

// @RequestParam(defaultValue = "1") int page,
// @RequestParam(defaultValue = "10") int size // 이것들이랑 똑같은 역할을 한다
public record Paging (
	int page,
	int size
	//todo: 정렬기능도 추가 예정
) {
		public PageRequest toPageable() {
			return PageRequest.of(page - 1, size);
		}
}

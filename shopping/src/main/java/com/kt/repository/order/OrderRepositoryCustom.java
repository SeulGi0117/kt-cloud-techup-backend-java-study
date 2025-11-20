package com.kt.repository.order;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kt.dto.order.OrderResponse;

public interface OrderRepositoryCustom {
	PageImpl<OrderResponse.Search> search(String keyword, Pageable pageable);
}

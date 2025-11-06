package com.kt.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Or;

import com.kt.commone.BaseEntity;
import com.kt.domain.order.Order;
import com.kt.domain.orderproduct.OrderProduct;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

public class Product extends BaseEntity {
	private String name;
	private Long price;
	private Long stock;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@OneToMany
	private List<OrderProduct> orderProducts = new ArrayList<>();

	// TODO: 생성, 수정, 삭제, 조회, 상태변경, 재고수량 증감
	// 생성
	// 수정
	// 삭제
	// 조회(리스트, 단건)
	// 상태변경하는 로직
	// 재고수량 감소
	// 재고수량 증가

}

package com.kt.domain.orderproduct;

import com.kt.commone.BaseEntity;
import com.kt.domain.order.Order;
import com.kt.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class OrderProduct extends BaseEntity {
	private Long quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	// 주문이 생성되면 OrderProduct도 같이 생성되게끔 해야한다.
	// todo: order, user 작업해야함. crud 작업도 해야함. api랑 문서화까지.
}
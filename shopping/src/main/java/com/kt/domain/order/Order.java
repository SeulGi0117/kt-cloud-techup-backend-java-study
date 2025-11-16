package com.kt.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Receiver;

import com.kt.commone.BaseEntity;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.domain.product.Product;
import com.kt.domain.user.User;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
// Order라는건 MySql의 예약어라 그대로 쓰면 에러가 난다.
@Table(name = "orders")
public class Order extends BaseEntity {
	@Embedded
	// 순수 자바 객체를 쓰고 싶을때 사용하는 어노테이션
	private Receiver receiver;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private LocalDateTime deliveredAy;

	// 연관관계를 생각해보자
	// 주문(N) <-> 회원(1). 회원은 주문 여러개 가능.
	// N : 1 => 다대일 관계
	// => JPA에서는 ManyToOne 라고한다.
	// FK => 많은 쪽에 생김(자동으로) 주문은 회원을 알고, 회원은 주문으 모르는상태. 단방향 고나계

	@ManyToOne
	// (fetch = FetchType.LAZY) LAZY를 걸어두면 처음에 정보 안가져옴, EAGER은 정보 다 가져오고.
	// order만 조회할때는 lazy가 유저라는 객체를 안만들어서 좋을수있다.
	// 근데 user를 빈번하게 조회를 하는경우? Eager이 더 낫다.
	@JoinColumn(name = "user_id") // user는 FK 많은 쪽에 생기니까 user_id 참고
	private User user;

	public Order(Receiver receiver,User user, LocalDateTime deliveredAy, ) {
		this.receiver = receiver;
		this.user = user;
		this.deliveredAy = deliveredAy;
		this.status = OrderStatus.PENDING;
	}
	// [1:N]하나의 오더는 여러개의 상품을 가질 수 있음
	// [1:N] 하나의 상품은 여러개의 오더를 가질 수 있다.

	@OneToMany
	private List<OrderProduct> orderProducts = new ArrayList<>();

	// todo: 주문생성, 주문상태변경, 주문생성완료 되면 재고차감,
	//  거의 생성의 의미만 있을듯? 배송받는사람 정보 변경, 주문취소,

	// 하나의 주문은 여러명의 회원을 가질 수 있나?(이런경우는 존재하지 않음)

}

package com.kt.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.aspectj.weaver.ast.Or;

import com.kt.commone.BaseEntity;
import com.kt.domain.order.Order;
import com.kt.domain.orderproduct.OrderProduct;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseEntity {
	private String name;
	private Long price;
	private Long stock;
	@Enumerated(EnumType.STRING)
	private ProductStatus status = ProductStatus.ACTIVATED;

	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderProducts = new ArrayList<>();

	// 생성 => 생성자를 통해. 상품의 초기값은 판매중이다
	public Product(String name, Long price, Long stock) {
		// TODO: Preconditons.validate 만들어둔거 쓰면 if문 숨길수있다. 이거 개선해야함
		// DDD 항상 코드 설계는 이렇게 해야함. 도메인에서 자기가잘 생성 될수있게 방어하는 로직
		if(Strings.isBlank(name)){
			throw new IllegalArgumentException("상품명은 필수입니다.");
		}
		if(price == null || price < 0){
			throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
		}
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	// 수정 - 정보수정, 상태수정
	public void update(String name, Long price, Long stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public void soldOut() {
		this.status = ProductStatus.SOLD_OUT;
	}

	public void inActivate() {
		this.status = ProductStatus.IN_ACTIVATED;
	}

	public void activate() {
		this.status = ProductStatus.ACTIVATED;
	}

	// 삭제 - 논리삭제
	// TODO: 진행 중인 주문이 있으면 안된다. 삭제 개선해보기
	// 그러면? Order order 불러와서 order.진행중인주문있니?(this) -> T F로 줌.
	// 그러면 그거에 따라서 삭제한다...하는 안정적인 로직
	public void delete() {
		this.status = ProductStatus.DELETED;
	}
	// 재고수량 감소
	public void decreaseStock(Long quantity){
		this.stock -= quantity;
	}
	// 재고수량 증가
	public void increaseStock(Long quantity){
		this.stock += quantity;
	}
	public boolean canProvide(Long quantity) {
		return this.stock >= quantity;
	}

	public void mapToOrderProduct(OrderProduct orderProduct) {
		this.orderProducts.add(orderProduct);
	}

	// TODO: 생성, 수정, 삭제, 조회, 상태변경, 재고수량 증감
	// 조회(리스트, 단건)
	// 상태변경하는 로직
}
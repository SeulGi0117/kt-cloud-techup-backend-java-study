package com.kt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.common.ErrorCode;
import com.kt.common.Preconditions;
import com.kt.domain.order.Order;
import com.kt.domain.order.Receiver;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.repository.ProductRepository;
import com.kt.repository.order.OrderProductRepository;
import com.kt.repository.order.OrderRepository;
import com.kt.repository.user.UserRepository;

import org.redisson.Redisson;

import lombok.RequiredArgsConstructor;

@Service // 서비스 비즈니스 로직을 처리하는 객체야 라고 알려주는 어노테이션
@Transactional // 더티체킹을 위해 붙여야하는거, 서비스 코드 변경 없이 해주는 어노테이션
@RequiredArgsConstructor // 롬복을 통해 생성자 주입 쉽게 하는거
public class OrderService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final OrderProductRepository orderProductRepository;

	// 주문생성 - 누가 주문했는지 알아야 한다.
	public void create(
		Long userId,
		Long productId,
		String receiverName,
		String receiverAddress,
		String receiverMobile,
		Long quantity
	) {
		// 재고가 충분한가?
		// 1. 직접 DB에 접근해서 물어본다
		// 2. product에게 물어본다.[OOP객체지향적] => 너 재고 수량 충분하니?
		// 3. 서비스에서 해결한다. var result = product.getStock() - quantity <0; 이런식으로 하는거.
		var product = productRepository.findByIdOrThrow(productId);

		Preconditions.validate(product.canProvide(quantity), ErrorCode.NOT_ENOUGH_STOCK);

		var user = userRepository.findByIdOrThrow(userId, ErrorCode.NOT_FOUND_USER);

		// TODO: redis import 가 안됨. 실습 코드 따라가면서 천천히 redis 실습해보기
		// 여기서 Lock을 획득 -> getLock에서 문자열을 인자로 줘야함
		// var rLock = redissonClient.getLock("stock");
		// rLock.tryLock()

		// TODO: 처리방법
		// 1. try catch finally
		// 2. 메소드레벨에서 throws 한다
		// Preconditions.validate(product.canProvide(quantity));
		var receiver = new Receiver(
			receiverName,
			receiverAddress,
			receiverMobile
		);

		var order = orderRepository.save(Order.create(receiver, user));
		var orderProduct = orderProductRepository.save(new OrderProduct(order, product, quantity));

		// 여기까지가 주문 생성 완료자나
		// 주문 생성 완료시에 재고 차감
		// -> 근데 재고 0인데 차감되면? -1로 터지잖슴 -> 재고 있는지 확인이 먼저임
		product.decreaseStock(quantity);

		product.mapToOrderProduct(orderProduct);
		order.mapToOrderProduct(orderProduct);
	}
}

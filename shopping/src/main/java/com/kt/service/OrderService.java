package com.kt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service // 서비스 처리하는거라고 알려주는 어노테이션
@Transactional
@RequiredArgsConstructor // 롬복에 DI 쉽게 하는거
public class OrderService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;


	// 주문생성 - 커밋될때 알아서 변경된거 날려줌
	public void create(Long userId,
		Long productId,
		String receiverAddress,
		String receiverMobile){

		// 재고가 충분한가?
		// 1. 직접 DB에 접근해서 물어본다
		// 2. product에게 물어본다. => 너 재고 수량 충분하니?
		// 3. 서비스에서 해결한다. var result = product.getStock() - quantity <0; 이런식으로 하는거.

		var product = productRepository.findByIdOrThrow(productId);
		Preconditions.product.canProvide(quantity)



		var user = userRepository.findAllByNameContationg(userId, ErrorCode.NOT_FOUND_USER);
		var receiver = new Receiver(
			receiverName,
			receiverAddress,
			receiverMobile
		);

		product.mapToOrderProduct(orderProduct);

		}
}

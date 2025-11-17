package com.kt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.commone.ErrorCode;
import com.kt.commone.Preconditions;
import com.kt.domain.order.Receiver;
import com.kt.repository.ProductRepository;
import com.kt.repository.user.UserRepository;

import org.redisson.Redisson;
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

		// TODO: redis import 가 안됨. 실습 코드 따라가면서 천천히 redis 실습해보기
		// 여기서 Lock을 획득 -> getLock에서 문자열을 인자로 줘야함
		var rLock = redissonClient.getLock("stock");
		rLock.tryLock()
		var product = productRepository.findByIdOrThrow(productId);

		// TODO: 처리방법
		// 1. try catch finally
		// 2. 메소드레벨에서 throws 한다
		Preconditions.validate(product.canProvide(quantity));

		var user = userRepository.findAllByNameContationg(userId, ErrorCode.NOT_FOUND_USER);
		var receiver = new Receiver(
			receiverName,
			receiverAddress,
			receiverMobile
		);

		product.mapToOrderProduct(orderProduct);

		}
}

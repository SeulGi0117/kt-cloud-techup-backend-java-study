package com.kt.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt.domain.user.User;
import com.kt.repository.ProductRepository;
import com.kt.repository.order.OrderRepository;
import com.kt.repository.user.UserRepository;

// TODO: OrderService Test Code 강의보고 복습하며 실습해야함
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class OrderServiceTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void 주문_생성(){
		// given
		var user = new userRepository.save();
		var product = new ProductRepository(

		)

		// when
		orderderivec.create(
			user.getId(),
			product.getId(),
			""
		)

		//then
		var foundedProduct = productRepository.findByIdOrThrow(product.getId());
		var foundedOrder = orderRepository.findAll()
	}

	@Test
	void 동시에_100명_주문() {

	}

}
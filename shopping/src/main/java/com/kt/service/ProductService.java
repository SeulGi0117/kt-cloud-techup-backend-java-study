package com.kt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.domain.product.Product;
import com.kt.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

// 1. request라는 DTO가 user까지 서비스로 갈경우
// 2. 인자로 전달 받는경우
// 3. Service DTO가 있는 경우
public class ProductService {
	private final ProductRepository productRepository;

	public void create(String name, Long price, Long quantity) {
		productRepository.save(new Product(
				name,
				price,
				quantity
			)
		);
	}

	public void update(Long id, String name, Long price, Long quantity) {
		// findById는 Optional 로 리턴되기 때문에 orElseThrow로 Exception 터트려주면됨
		var product = productRepository.findByIdOrThrow(id);

		// 굳이 save하지 않아도, 더티체킹에 의해서 변경점 감지되면
		// 트랜잭션이 끝난 뒤 업데이트 쿼리 날려준다.
		product.update(name, price, quantity);
	}

	public void activate(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.activate();
	}

	public void inActivate(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.inActivate();
	}

	public void soldOut(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.soldOut();
	}

	public void delete(Long id) {
		var product = productRepository.findByIdOrThrow(id);

		product.delete();
	}

	public void decreaseStock(Long id, Long quantity) {
		var product = productRepository.findByIdOrThrow(id);

		product.decreaseStock(quantity);
	}

	public void increaseStock(Long id, Long quantity) {
		var product = productRepository.findByIdOrThrow(id);

		product.increaseStock(quantity);
	}
}

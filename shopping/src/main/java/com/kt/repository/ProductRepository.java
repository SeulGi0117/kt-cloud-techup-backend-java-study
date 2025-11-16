package com.kt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.commone.CustomException;
import com.kt.commone.ErrorCode;
import com.kt.domain.product.Product;

import ch.qos.logback.core.spi.ErrorCodes;

public interface ProductRepository extends JpaRepository<Product, Long> {
	default Product findByIdOrThrow(Long id){
		return findById(id).orElseThrow(()-> new CustomException(ErrorCode.DOES_NOT_MATCH_OLD_PASSWORD));
	}

	// select * from product where nmae = ?
	// 내손으로 작성한건 무조건 다 테스트 해야한다.
	Optional<Product> findByName(String name);
}

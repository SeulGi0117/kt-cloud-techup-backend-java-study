package com.kt.repository.order;

import static org.springframework.data.relational.core.sql.StatementBuilder.*;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;	// Bean을 등록해야만 쓸 수 있다. 빈 등록여부 확인할줄 알아야한다.
	// QClass를 임포트해서 쿼릴르 작성할 때 사용하면 된다
	private final QOrder order = QOrder.order;
	private final QOrderProduct orderProduct = QOrderProduct.orderProduct;
	private final QProduct QProduct = QProduct.product;


	public void serach(
		String keyword,
		Paging pageable
	){
		// 페이징을 구현할때 offset(0번부터 얼마나 떨어져있는지), limit(한페이지에 몇개 보여줄건지)을 사용한다.
		// selcet에 order넣으면 *랑 똑같음. select(order).from(order) 똑같으면 .selectFrom(order) 이렇게 쓰면 됨
		var content = jpaQueryFactory
			.select(order)
			.from(order)
			.leftJoin(orderProduct).on(orderProduct.order.id.eq(order.id))
			.join(product).on(orderProduct.product.id.eq(product.id)) // join의 default는 inner
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();	// fetch를 사용해야 쿼리가 나간다. 리스트 반환. One은 order 딱 하나 나감 단건조회 같은거.

			// 최초 페이지에 접근했을때 -> 전체검색이 되야할까 아니면 특정키워드검생이 자동으로 되야하나
			// nmae like '%null%'
			// keyword = null

		// 총갯수
		// 현재 몇개볼건지 / 총갯수 = 총 몇페이지 볼건지?
		var total = jpaQueryFactory
			.select(order.id)
			// todo: join들도 다 들어와 줘야한다. 검색했을때 페이지수도 달라지니까.




	}
}

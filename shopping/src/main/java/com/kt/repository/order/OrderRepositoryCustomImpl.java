package com.kt.repository.order;

import static com.kt.domain.product.QProduct.*;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kt.domain.order.QOrder;
import com.kt.domain.orderproduct.QOrderProduct;
import com.kt.domain.product.QProduct;
import com.kt.dto.order.OrderResponse;
import com.kt.dto.order.QOrderResponse_Search;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;  // Bean을 등록해야만 쓸 수 있다. 빈 등록여부 확인할줄 알아야한다.
	// QClass를 임포트해서 쿼릴르 작성할 때 사용하면 된다
	private final QOrder order = QOrder.order;
	private final QOrderProduct orderProduct = QOrderProduct.orderProduct;
	private final QProduct QProduct = product;

	@Override
	public PageImpl<OrderResponse.Search> search(
		String keyword,
		Pageable pageable
	) {
		// 페이징을 구현할때 offset(0번부터 얼마나 떨어져있는지), limit(한페이지에 몇개 보여줄건지)을 사용한다.
		// selcet에 order넣으면 *랑 똑같음. select(order).from(order) 똑같으면 .selectFrom(order) 이렇게 쓰면 됨

		// booleanBuilder, BooleanExpression
		var booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(containsProductName(keyword));

		// booleanBuilder.and()
		// booleanBuilder.or()
		// booleanBuilder 안에다가 BooleanExpression을 추가해주는 방식으로

		var content = jpaQueryFactory
			.select(new QOrderResponse_Search(
				order.id,
				order.receiver.name,
				product.name,
				orderProduct.quantity,
				Expressions.asNumber(0L), //, "totalPrice" 더미데이터
				order.status,
				order.createdAt
			)) // .selectFrom(order) 둘다 똑같은거면 이렇게 써도 됨
			.from(order)
			.leftJoin(orderProduct)
			.on(orderProduct.order.id.eq(order.id)) // FK는 생략가능함(.on어쩌구 쓰는거), left join 은 null이어도 조회가 가능하다
			.join(product)
			.on(orderProduct.product.id.eq(product.id)) // join의 default는 inner // inner join대상이 null이면 조회 자체가 안된다.
			.where(booleanBuilder)
			.orderBy(order.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();  // fetch를 사용해야 쿼리가 나간다. 리스트 반환. One은 order 딱 하나 나감 단건조회 같은거.

		// 최초 페이지에 접근했을때 -> 전체검색이 되야할까 아니면 특정키워드검생이 자동으로 되야하나
		// nmae like '%null%' (동작 해야하나?) -> 동작하면 안됨
		// keyword = null / 키워드가 null이 아닐때만 where 절에 들어가도록...

		// 프론트에 페이지 보내줄때 총갯수 계산해서 구해줘얗나다.
		// 현재 몇개볼건지 / 총갯수 = 총 몇페이지 볼건지?
		// var total = jpaQueryFactory
		// 	.select(order.id)
		// todo: join들도 다 들어와 줘야한다. 검색했을때 페이지수도 달라지니까.

		var total = (long)jpaQueryFactory.select(order.id)
			.from(order)
			.leftJoin(orderProduct).on(orderProduct.order.id.eq(order.id)) // FK는 생략가능함(.on어쩌구 쓰는거), left join 은 null이어도 조회가 가능하다
			.join(product).on(orderProduct.product.id.eq(product.id)) // join의 default는 inner // inner join대상이 null이면 조회 자체가 안된다.
			.where(booleanBuilder)
			.fetch().size();

		return new PageImpl<>(content, pageable, total);
	}

	// 시작하는 '%keyword'
	// 끝나는 'keyword%'
	// 포함하는 '%keyword%'
	// 공백이면 어쩌징? '%" "%' 이렇게 들어오면 어쩌지?? -> isBlank

	private BooleanExpression containsProductName(String keyword) {
		// if (keyword != null && !keyword.isBlank()) {
		// 	return product.name.containsIgnoreCase(keyword); // containsIgnoreCase는 대소문자 안가리는것
		// 	// 시작하는 -> startWith, 끝나는 -> endWith
		// }

		// Strings, Objects 이렇게 reference 타입에 s 붙는 애들. support 라는 뜻~ 이렇게 삼항연산자로 깔끔하게 가능
		return Strings.isNotBlank(keyword) ? product.name.containsIgnoreCase(keyword) : null;
	}
}

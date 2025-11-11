package com.kt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// spring이 bean으로 등록할떄 제일 먼저 등록함. 설정파일이라고 알려주는 어노테이션
@Configuration
public class QueryDslConfiguration {
	@PersistenceContext
	private EntityManager entityManager; // qeuryDsl, test 요 2가지에서만 쓰게됨
	// querydsl은 컴퍼일러(QClass-컴파일러가 인식하는 클래스)기반으로 동적쿼리를 생성해주는 라이브러리
	// QClass == Entity 라고 생각하면 됨. QProduct이식으로 Q를 붙여서 만들어준다 <= 일단 얘 알아야됨
	// => 컴파일러라 문법적으로 도움받기 가능.
	// QClass, BooleanExpression, BooleanBuilder 알아야 된다. // 동적쿼리 => 뭐 조건에 따라 붙이고 안붙이고 동적으로 해주는거.

	// querydsl 사용방법 2가지
	// 1. {domain}RepositoryCustom + {domain}RepositoryImpl // 정석적
	// 2. {domain}query => 클래스를 만들어서 사용. // 스프링을 이용해서 간편하게 쓰는 방법

	@Bean // todo 이거 뭐하는 애인지 공부하고 복습하기
	public JPAQueryFactory jpaQueryFactory(){
		return new JPAQueryFactory(entityManager);
	}
}

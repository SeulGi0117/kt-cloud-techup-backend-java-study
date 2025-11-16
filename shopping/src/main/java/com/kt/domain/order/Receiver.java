package com.kt.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

// POJO를 엔티티의 일부로 포함시켜서 테이블 컬럼으로 매핑하기 해준다.
// 테이블에는 별도 테이블이 생기지 않고 해당 객체의 필드들이 엔티티의 컬럼으로 함께 들어간다.
@Embeddable
@Getter
public class Receiver {
	// order에서 중복되는 receiver는 VO로 만듬
	// DB에서 receiverName 이 컬럼들과 조합해서 자동으로 맵핑해준다.
	@Column(name = "receiver_name")
	private String name;
	@Column(name= "receiver_address")
	private String address;
	@Column(name = "receiver_mobile")
	private String mobile;

}

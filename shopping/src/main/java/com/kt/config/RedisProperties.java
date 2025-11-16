package com.kt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

// TODO: @ConfigurationProperties 공부하기 application.yml 에 있는 걸 POJO로 쓸수있게 가져오는방법
// value를 쓰지 않고 이렇게 쓴다. value는 재할당 할 수 있어서 불변성을 못지킴.
@ConfigurationProperties("spring.redis")
@ConfigurationPropertiesScan // Scan 도 같이 써야한다
public class RedisProperties {
}

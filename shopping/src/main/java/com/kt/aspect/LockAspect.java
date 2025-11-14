package com.kt.aspect;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

// TODO: @Aspect “동시성 제어(락)”를 어떻게 설계할 거냐 -> AOP로 락 획득/해제 추상화, Around 함수의 시작과 끝에서동작샇ㄹ 수있게 해주는 어노테이션
@Aspect
@Component
@RequiredArgsConstructor
public class LockAspect {
	private final RedissonClient redissonClient;

	// TODO: Around 어노테이션 공부
	@Around("execution(* com.kt.service.OrderService.create(..))")
	public Object lock(ProceedingJoinPoint joinPoint) throws {
		var rLock = redissonClient.getLock("stock");
		var available = rLock.tryLock(0L, 300L, TimeUnit.SECONDS);

			if(!available){
				System.out.println("락 획득 실패");
				return false;
			}
			return joinPoint.proceed();
	} final {
		if(Objects.nonNull(rLock)){
			rLock.unlock();
		}
	}
}

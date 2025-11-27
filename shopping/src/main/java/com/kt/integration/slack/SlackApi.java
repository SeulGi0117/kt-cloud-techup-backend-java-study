package com.kt.integration.slack;

import org.springframework.stereotype.Component;

import com.slack.api.methods.MethodsClient;

import lombok.RequiredArgsConstructor;

// 실제로 슬랙으로 알림을 보낼 것
@Component // 얘는 스프링의 소속도 아니고 설정파일을 설정한느것도 아님. 그냥 스프링에서 부품처럼 달아 사용하는 느낌으로
@RequiredArgsConstructor
public class SlackApi {
	// 앤 IoC 컨테이너에 없음. Bean으로 만들어줘야한다. 그래야 DI로 받아올수있음
	private final MethodsClient slackClient;

	// throws는 호출한 곳으로 부모로 에러가 전달이 되기 때문에 우리 서비스에 영향을 미친다. 외부 서비스는 try catch로 해줘야한다
}

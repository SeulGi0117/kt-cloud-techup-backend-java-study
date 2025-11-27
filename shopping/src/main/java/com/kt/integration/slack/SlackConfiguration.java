package com.kt.integration.slack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;

@Configuration
public class SlackConfiguration {
	@Bean
	public MethodsClient methodsClient(){
		// 슬랙 봇 토큰 넣어주면 됨
		return Slack.getInstance().methods("token");
	}
}

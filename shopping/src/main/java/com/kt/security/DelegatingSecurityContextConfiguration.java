package com.kt.security;

import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class DelegatingSecurityContextConfiguration {
	@Bean
	@ConditionalOnMissingFilterBean(DelegatingSecurityContextAsyncTaskExecutor.class)
	public DelegatingSecurityContextAsyncTaskExecutor DelegatingSecurityContextAsyncTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor
		{
			return new DelegatingSecurityContextFilter();
		}

	}

}

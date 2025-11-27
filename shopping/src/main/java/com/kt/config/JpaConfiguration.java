package com.kt.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class JpaConfiguration {
	@Bean
	// 얘 제네릭이라 타입을 Long으로 하는지 String으로 하는지 넣어줘야됨~
	public AuditorAware<Long> auditorProvider() {
		return () -> {
			var principal = (DefaultCurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("AuditorAware user Id: "+principal);
			return Optional.of(principal.getId());
		};
	}
}

package com.kt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.persistence.GeneratedValue;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "테크업 쇼핑몰",
		description = "테크업 쇼핑몰 API 명세서"
	)
)
public class SwaggerConfiguration {
	private final Environment environment;

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.components(
				new Components()
					.addSecuritySchemes(
						"Bearer Authentication",
						new SecurityScheme()
							.type(SecurityScheme.Type.HTTP)
							.scheme("bearer")
							.bearerFormat("JWT"))
			);
	}
	private String getServerUrlL(){
		var profile = getEnv();
		return switch (profile)
	}

	private String getEnv() {
		return environment.getActiveProfiles()[0];
	}

}

package com.springapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

		httpSecurity
		       .authorizeExchange()
		       .anyExchange()
		       .authenticated()
		       .and()
		       .oauth2Login()
		       .and()
		       .oauth2ResourceServer()
				.jwt();

		return httpSecurity.securityContextRepository(securityContextRepository()).build();
	}

	@Bean
	public ServerSecurityContextRepository securityContextRepository() {
		return new WebSessionServerSecurityContextRepository();
	}

}

package com.spring.lcwd.user.service.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfig {

	@Bean
	public OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager =
				                                                   new DefaultOAuth2AuthorizedClientManager(
				                                                  clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		
		OAuth2AuthorizedClientProvider provider =
                                       OAuth2AuthorizedClientProviderBuilder
                                       .builder()
                                       .clientCredentials()
                                       .build();

		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

		return defaultOAuth2AuthorizedClientManager;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
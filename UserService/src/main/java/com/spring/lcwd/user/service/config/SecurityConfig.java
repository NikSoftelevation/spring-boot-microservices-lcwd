package com.spring.lcwd.user.service.config;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.spring.lcwd.user.service.config.interceptor.RestTemplateInterceptor;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	@Autowired
	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;

	@Bean
	public OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, oAuth2AuthorizedClientRepository);

		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials()
				.build();

		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

		return defaultOAuth2AuthorizedClientManager;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();

		interceptors.add(new RestTemplateInterceptor(auth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository)));

		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
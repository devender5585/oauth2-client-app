package com.dev.client.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                                OAuth2AuthorizedClientRepository authorizedClientRepository) {

        // 1. Authorized Client Manager jo tokens ko handle karega
        DefaultOAuth2AuthorizedClientManager authorizedClientManager = 
                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);

        // 2. OAuth2 Filter jo automatic "Bearer Token" header mein add karega
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2ClientFilter =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        
        oauth2ClientFilter.setDefaultClientRegistrationId("client-app");

        return WebClient.builder()
                .apply(oauth2ClientFilter.oauth2Configuration())
                .build();
    }
}
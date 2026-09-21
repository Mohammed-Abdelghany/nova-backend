package com.example.pharmaglowback.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(TelegramProperties.class)
public class TelegramConfig {

    @Bean
    public RestClient telegramRestClient() {
        return RestClient.builder()
                .baseUrl("https://api.telegram.org")
                .build();
    }
}

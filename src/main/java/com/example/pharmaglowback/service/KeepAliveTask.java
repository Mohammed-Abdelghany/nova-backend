package com.example.pharmaglowback.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class KeepAliveTask {

    private final RestClient restClient = RestClient.create();

    @Value("${app.keep-alive.url:}")
    private String keepAliveUrl;

    @Scheduled(fixedRate = 300_000)
    public void ping() {
        if (keepAliveUrl == null || keepAliveUrl.isBlank()) {
            return;
        }

        try {
            restClient.get().uri(keepAliveUrl + "/actuator/health").retrieve().toBodilessEntity();
            log.info("Keep-alive ping sent to {}", keepAliveUrl);
        } catch (Exception e) {
            log.warn("Keep-alive ping to {} failed: {}", keepAliveUrl, e.getMessage());
        }
    }
}

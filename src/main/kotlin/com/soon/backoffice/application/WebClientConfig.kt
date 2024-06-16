package com.soon.backoffice.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
                .baseUrl("http://localhost:8083") //TODO 후에 변경 필요
                .build()
    }
}
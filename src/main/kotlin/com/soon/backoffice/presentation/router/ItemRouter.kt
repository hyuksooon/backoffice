package com.soon.backoffice.presentation.router

import com.soon.backoffice.presentation.handler.item.ItemHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ItemRouter(private val itemHandler: ItemHandler) {
    @Bean
    fun itemRoute(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/common/backoffice/item").nest{
                GET("",itemHandler::getItem)
            }
        }
    }
}
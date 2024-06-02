package com.soon.backoffice.presentation.router

import com.soon.backoffice.presentation.handler.QnaHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class QnaRouter(private val qnaHandler: QnaHandler) {
    @Bean
    fun memberRoute(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/common/backoffice/qna").nest{
                POST("",qnaHandler::createQna)
            }
        }
    }
}
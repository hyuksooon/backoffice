package com.soon.backoffice.presentation.extension

import com.soon.backoffice.domain.extension.toModel
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBodyOrNull

fun ServerRequest.extractExampleHeader() :Any{
    return headers().header("example").firstOrNull()
            ?.let{
                it.toModel<Any>()
            }?:throw IllegalArgumentException()
}
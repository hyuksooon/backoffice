package com.soon.backoffice.presentation.extension

import com.soon.backoffice.domain.extension.decodeBase64ToDto
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.queryParamOrNull

fun ServerRequest.extractMemberCodeHeader() : MemberHeader {
    return headers().header("Member-Code").firstOrNull()
    ?.let{
        it.decodeBase64ToDto<MemberHeader>()
    }?:throw IllegalArgumentException()
}

fun ServerRequest.extractServiceCodeHeader() :ServiceHeader{
    return headers().header("Service-Code").firstOrNull()
        ?.let{
            it.decodeBase64ToDto<ServiceHeader>()
        }?:throw IllegalArgumentException()
}

fun ServerRequest.intQueryParam(parameter: String): Int {
    return queryParamOrNull(parameter)?.toIntOrNull()
            ?: throw IllegalArgumentException("Invalid or missing 'itemNo' query parameter")
}
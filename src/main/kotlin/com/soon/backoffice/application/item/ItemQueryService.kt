package com.soon.backoffice.application.item

import com.soon.backoffice.application.item.model.ItemResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull

import org.springframework.stereotype.Service

@Service
class ItemQueryService(
        private val webClient: WebClient,
)
{
    suspend fun getItem(itemNo:Int):ItemResponse {
        return webClient.get()
                .uri("/internal/common/item?itemNo={itemNo}", itemNo)
                .retrieve()
                .awaitBodyOrNull<ItemResponse>()?: throw IllegalArgumentException()
    }
}
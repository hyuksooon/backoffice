package com.soon.backoffice.presentation.handler.item

import com.soon.backoffice.application.item.ItemQueryService
import com.soon.backoffice.application.util.coroutines.ApplicationDispatchers
import com.soon.backoffice.presentation.extension.intQueryParam
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait


@Service
class ItemHandler(
         val itemQueryService: ItemQueryService,
) {
    suspend fun getItem(request: ServerRequest): ServerResponse = withContext(ApplicationDispatchers.IO) {
        val itemNo=request.intQueryParam("itemNo")
        ServerResponse.ok().bodyValueAndAwait(itemQueryService.getItem(itemNo))
    }

}
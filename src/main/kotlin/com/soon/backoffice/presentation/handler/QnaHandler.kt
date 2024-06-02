package com.soon.backoffice.presentation.handler

import com.soon.backoffice.application.qna.QnaCommandService
import com.soon.backoffice.application.util.coroutines.ApplicationDispatchers
import com.soon.backoffice.presentation.handler.model.QnaCreateRequest
import com.soon.member.presentation.extension.extractMemberCodeHeader
import com.soon.member.presentation.extension.extractServiceCodeHeader
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*

@Service
class QnaHandler(
        private val qnaCommandService: QnaCommandService
) {
    suspend fun createQna(request: ServerRequest): ServerResponse = withContext(ApplicationDispatchers.IO) {
        val memberHeader=request.extractMemberCodeHeader()
        val serviceHeader=request.extractServiceCodeHeader()
        val command=request.awaitBodyOrNull<QnaCreateRequest>()?.toCommand(memberHeader.no,serviceHeader.no)?:throw IllegalArgumentException()

        qnaCommandService.createQna(command)
        ServerResponse.noContent().buildAndAwait()
    }

}
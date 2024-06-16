package com.soon.backoffice.application.qna

import com.soon.backoffice.application.qna.model.QnaCreateCommand
import com.soon.backoffice.application.util.executeWithContext
import com.soon.backoffice.domain.qna.Qna
import com.soon.backoffice.domain.qna.QnaRepository
import com.soon.backoffice.domain.tag.QnaTag
import com.soon.backoffice.domain.tag.QnaTagRepository
import com.soon.backoffice.domain.tag.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class QnaCommandService(
    private val qnaRepository: QnaRepository,
    private val tagRepository: TagRepository,
    private val qnaTagRepository: QnaTagRepository,
    private val transactionTemplate: TransactionTemplate
)
{
    suspend fun createQna(command: QnaCreateCommand) {
        val tags = tagRepository.findAllByNoIsIn(command.tags.distinct())
        if(tags.size!=command.tags.size) throw IllegalArgumentException()

        transactionTemplate.executeWithContext {
            val qna = qnaRepository.save(Qna.toQna(command))
            tags.map {
                qnaTagRepository.save(QnaTag.toQnaTag(qna.no,it.no))
            }
        }
    }
}
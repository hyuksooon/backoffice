package com.soon.backoffice.presentation.handler.qna.model

import com.soon.backoffice.application.qna.model.QnaCreateCommand

data class QnaCreateRequest(
    val title:String,
    val body:String,
    val tags: List<Int>,
    val password:String?,
    ){
    fun toCommand(memberNo:Int, serviceNo:Int)= QnaCreateCommand(
        memberNo=memberNo,
        serviceNo=serviceNo,
        title=title,
        body=body,
        tags=tags,
        password=password,
    )
}
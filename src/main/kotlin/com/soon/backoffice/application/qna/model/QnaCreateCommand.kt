package com.soon.backoffice.application.qna.model

data class QnaCreateCommand (
        val memberNo:Int,
        val serviceNo:Int,
        val title:String,
        val body:String,
        val tags: List<Int>,
        val password:String?,
)
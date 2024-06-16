package com.soon.backoffice.application.item.model

data class ItemResponse(
        val itemNo: Int,
        val serviceNo: Int,
        val title: String,
        val description: String,
        val thumbnail: String
)
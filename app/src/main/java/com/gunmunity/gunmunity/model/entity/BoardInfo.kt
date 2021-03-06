package com.gunmunity.gunmunity.model.entity

import java.io.Serializable

data class BoardInfo (
    var author: String,
    var boardCategory: String,
    val content: String,
    val createdDate: String,
    val id: Long,
    val modifiedDate: String,
    val recommendCount: Int,
    val title: String
) : Serializable
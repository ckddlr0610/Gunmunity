package com.example.gunmunity.model.entity

import java.io.Serializable

data class BoardInfo (
    var author: String,
    var boardCategory: String,
    val content: String,
    val createdDate: String,
    val id: Long,
    val modifiedDate: String,
    val title: String
) : Serializable
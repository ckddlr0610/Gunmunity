package com.gunmunity.gunmunity.model

data class CreateBoardRequest (
    val boardCategory : String,
    val content: String,
    val title: String
)
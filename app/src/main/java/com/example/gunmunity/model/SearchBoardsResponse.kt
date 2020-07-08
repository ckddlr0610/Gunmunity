package com.example.gunmunity.model

import com.example.gunmunity.model.entity.BoardInfo

data class SearchBoardsResponse (
    val boardsCount : Long,
    val boardsInfo: ArrayList<BoardInfo>,
    val currentPage: Int,
    val totalPage: Int
)
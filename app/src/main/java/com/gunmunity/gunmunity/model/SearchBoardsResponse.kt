package com.gunmunity.gunmunity.model

import com.gunmunity.gunmunity.model.entity.BoardInfo

data class SearchBoardsResponse (
    val boardsCount : Long,
    val boardsInfo: ArrayList<BoardInfo>,
    val currentPage: Int,
    val totalPage: Int
)
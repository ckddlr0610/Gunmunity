package com.gunmunity.gunmunity.model

import com.gunmunity.gunmunity.model.entity.CommentInfo

data class GetCommentsResponse (
    val commentsCount : Long,
    val commentsInfo: ArrayList<CommentInfo>
)
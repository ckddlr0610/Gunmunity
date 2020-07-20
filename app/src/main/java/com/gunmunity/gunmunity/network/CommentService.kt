package com.gunmunity.gunmunity.network

import com.gunmunity.gunmunity.model.CreateCommentResponse
import com.gunmunity.gunmunity.model.GetCommentsResponse
import io.reactivex.Single
import retrofit2.http.*


interface CommentService {
    @GET("/v1/boards/{boardId}/comments")
    fun getCommentList(@Path("boardId") boardId: Long): Single<GetCommentsResponse>

    @POST("/v1/boards/{boardId}/comments/comment")
    fun postComment (
        @Header("accessToken") accessToken: String,
        @Path("boardId") boardId: Long,
        @Body content: String
    ): Single<CreateCommentResponse>
}
package com.gunmunity.gunmunity.network

import com.gunmunity.gunmunity.model.*
import io.reactivex.Single
import retrofit2.http.*

interface CommunityService {
    @GET("/v1/boards")
    fun getBoardList(@Query("boardCategory") boardCategory : String,
                     @Query("currentPage") currentPage : Int) : Single<SearchBoardsResponse>

    @POST("/v1/boards/board")
    fun postArticle(@Header("accessToken") accessToken : String,
                    @Body createBoardRequest: CreateBoardRequest) : Single<BoardInfoResponse>

    @GET("/v1/boards/popular")
    fun getPopularList(@Query("currentPage") currentPage: Int) : Single<SearchBoardsResponse>

    @POST("/v1/boards/{boardId}/recommendation")
    fun recommendBoard(@Header("accessToken") accessToken : String,
                    @Path("boardId") boardId : Long) : Single<BoardRecommendationResponse>

    @HTTP(method="DELETE", hasBody=true, path="/v1/boards/{boardId}/recommendation")
    fun recommendBoardCancel(@Header("accessToken") accessToken : String,
                       @Path("boardId") boardId : Long) : Single<BoardRecommendationResponse>
}
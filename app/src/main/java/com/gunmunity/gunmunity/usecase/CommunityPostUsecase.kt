package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.BoardInfoResponse
import com.gunmunity.gunmunity.model.CreateBoardRequest
import com.gunmunity.gunmunity.network.CommunityService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommunityPostUsecase {
    private val communityService: CommunityService = RetrofitManager.create(CommunityService::class.java)

    fun postArticle(accessToken: String, boardCategory: String,
                    content: String, title: String): Single<BoardInfoResponse> {
        val createBoardRequest = CreateBoardRequest(boardCategory, content, title)
        return communityService.postArticle(accessToken, createBoardRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
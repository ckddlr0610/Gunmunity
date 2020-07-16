package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.SearchBoardsResponse
import com.gunmunity.gunmunity.network.CommunityService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommunityListUsecase {
    private val communityService: CommunityService = RetrofitManager.create(CommunityService::class.java)

    fun getBoardList(boardCategory: String, currentPage: Int): Single<SearchBoardsResponse> {
        return communityService.getBoardList(boardCategory, currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
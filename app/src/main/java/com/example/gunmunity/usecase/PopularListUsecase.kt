package com.example.gunmunity.usecase

import com.example.gunmunity.model.SearchBoardsResponse
import com.example.gunmunity.network.CommunityService
import com.example.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularListUsecase {
    private val communityService: CommunityService = RetrofitManager.create(CommunityService::class.java)

    fun getBoardList(currentPage: Int): Single<SearchBoardsResponse> {
        return communityService.getPopularList(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
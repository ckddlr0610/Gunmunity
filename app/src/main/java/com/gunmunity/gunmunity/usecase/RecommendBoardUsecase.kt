package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.GunmunityApplication
import com.gunmunity.gunmunity.model.BoardRecommendationResponse
import com.gunmunity.gunmunity.network.CommunityService
import com.gunmunity.gunmunity.util.ConstValue
import com.gunmunity.gunmunity.util.RetrofitManager
import com.gunmunity.gunmunity.util.SharedPreferenceManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecommendBoardUsecase {
    private val communityService: CommunityService = RetrofitManager.create(CommunityService::class.java)

    fun recommendBoard(boardId : Long): Single<BoardRecommendationResponse> {
        return communityService.recommendBoard(
            SharedPreferenceManager.getStringPref(ConstValue.CONST_ACCESS_TOKEN), boardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun recommendBoardCancel(boardId : Long): Single<BoardRecommendationResponse> {
        return communityService.recommendBoardCancel(
            SharedPreferenceManager.getStringPref(ConstValue.CONST_ACCESS_TOKEN), boardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
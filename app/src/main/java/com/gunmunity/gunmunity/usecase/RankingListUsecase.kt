package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.DS_MND_PX_PARD_PRDT_INFO
import com.gunmunity.gunmunity.model.RankingResponse
import com.gunmunity.gunmunity.network.RankingService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class RankingListUsecase {
    private val rankingService: RankingService = RetrofitManager.createFromPublic(RankingService::class.java)

    fun getRankingList(startIndex : Int, endIndex : Int): Single<RankingResponse> {
        return rankingService.getRankingList(0,3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRankingList1(startIndex: Int, endIndex: Int): Call<DS_MND_PX_PARD_PRDT_INFO> {
        return rankingService.getRankingList2(1, 10)
    }
}
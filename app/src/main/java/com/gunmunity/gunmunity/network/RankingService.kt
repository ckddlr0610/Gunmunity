package com.gunmunity.gunmunity.network

import com.gunmunity.gunmunity.model.DS_MND_PX_PARD_PRDT_INFO
import com.gunmunity.gunmunity.model.RankingResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RankingService {
    @GET("sample/xml/DS_MND_PX_PARD_PRDT_INFO/{startIndex}/{endIndex}/")
    fun getRankingList(@Path("startIndex") startIndex : Int,
                       @Path("endIndex") endIndex: Int) : Single<RankingResponse>

    @GET("3736313630333934323630313032303739/json/DS_MND_PX_PARD_PRDT_INFO/{startIndex}/{endIndex}/")
    fun getRankingList2(@Path("startIndex") startIndex : Int,
                       @Path("endIndex") endIndex: Int) : Call<DS_MND_PX_PARD_PRDT_INFO>
}
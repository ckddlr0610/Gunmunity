package com.example.gunmunity.presentation.ranking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gunmunity.model.DS_MND_PX_PARD_PRDT_INFO
import com.example.gunmunity.model.PxInfo
import com.example.gunmunity.model.entity.RankingInfo
import com.example.gunmunity.usecase.RankingListUsecase
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Callback
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class RankingViewModel : ViewModel() {
    private val rankingListUseCase = RankingListUsecase()
    val rankingInfos : MutableLiveData<List<RankingInfo>> = MutableLiveData()

    fun getRankingList() {
        rankingListUseCase.getRankingList(0, 3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                rankingInfos.value = it.rankingInfos
            }, {
                Log.d("MyTag", it.localizedMessage)
            })
    }

    fun getRankingList2() {
        rankingListUseCase.getRankingList1(0, 3).enqueue(object : retrofit2.Callback<DS_MND_PX_PARD_PRDT_INFO> {
            override fun onFailure(call: Call<DS_MND_PX_PARD_PRDT_INFO>, t: Throwable) {
            }

            override fun onResponse(call: Call<DS_MND_PX_PARD_PRDT_INFO>, response: Response<DS_MND_PX_PARD_PRDT_INFO>) {
                val result = response.body()
                Log.d("MyTag", result.toString())
                //val jsonObject = JSONObject(result)
                val pxInfo : PxInfo = result!!.DS_MND_PX_PARD_PRDT_INFO
                rankingInfos.value = pxInfo.row
                /*pxInfo.list_total_count = jsonObject.getInt("list_total_count")

                val pxArray = jsonObject.getJSONArray("row")
                val length = pxArray.length()

                for (i in 0..length) {
                    val pxObject = pxArray.getJSONObject(i)
                    pxInfo.row?.get(i)?.prdtnm = pxObject.getString("prdtnm")
                    pxInfo.row?.get(i)?.rowno = pxObject.getInt("rowno")
                    pxInfo.row?.get(i)?.sellmonth = pxObject.getInt("sellmonth")
                    pxInfo.row?.get(i)?.seltnstd = pxObject.getString("seltnstd")
                    pxInfo.row?.get(i)?.sellyear = pxObject.getInt("sellyear")

                    rankingInfos.value?.add(pxInfo.row!!.get(i))
                }*/

            }

        }
        )
    }
}
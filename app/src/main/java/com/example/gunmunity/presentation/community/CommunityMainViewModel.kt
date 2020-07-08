package com.example.gunmunity.presentation.community

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gunmunity.model.Category
import com.example.gunmunity.model.entity.BoardInfo
import com.example.gunmunity.usecase.CommunityListUsecase
import com.example.gunmunity.usecase.PopularListUsecase
import com.example.gunmunity.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommunityMainViewModel : ViewModel() {
    private val communityListUsecase = CommunityListUsecase()
    private val communityPopularListUsecase = PopularListUsecase()
    private val TAG = "CommunityMainViewModel"
    private var category : String = ""
    val emptyListCall: SingleLiveEvent<Void> = SingleLiveEvent()
    val getListCall: SingleLiveEvent<Void> = SingleLiveEvent()
    val boardInfos : MutableLiveData<ArrayList<BoardInfo>> = MutableLiveData()

    fun clickCategory(viewType: Int) {
        when (viewType) {
            1 -> {
                category = Category.COUNSEL.toString()
                getBoardList(category, 0)
            }
            2 -> {
                category = Category.INFORMATION.toString()
                getBoardList(category, 0)
            }
            3 -> {
                category = Category.FREE.toString()
                getBoardList(category, 0)
            }
            4 -> getPopularList(0)
        }
    }

    fun getBoardList(boardCategory: String, currentPage: Int) {
        communityListUsecase.getBoardList(boardCategory, currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.boardsInfo == null) {
                    emptyListCall.call()
                } else {
                    boardInfos.value = it.boardsInfo
                    getListCall.call()
                }
            }, {
                Log.d(TAG, it.localizedMessage)
            })
    }

    fun getPopularList(currentPage: Int) {
        communityPopularListUsecase.getBoardList(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.boardsInfo == null) {
                    emptyListCall.call()
                } else {
                    boardInfos.value = it.boardsInfo
                    getListCall.call()
                }
            }, {
                Log.d(TAG, it.localizedMessage)
            })
    }
}
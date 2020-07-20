package com.gunmunity.gunmunity.presentation.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunmunity.gunmunity.model.entity.CommentInfo
import com.gunmunity.gunmunity.usecase.GetCommentListUsecase
import com.gunmunity.gunmunity.usecase.PostCommentUsecase
import com.gunmunity.gunmunity.usecase.RecommendBoardUsecase
import com.gunmunity.gunmunity.usecase.RefreshTokenUsecase
import com.gunmunity.gunmunity.util.ConstValue
import com.gunmunity.gunmunity.util.SharedPreferenceManager
import com.gunmunity.gunmunity.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommunityDetailViewModel(
    private val recommendBoardUsecase: RecommendBoardUsecase = RecommendBoardUsecase(),
    private val postCommentUsecase: PostCommentUsecase = PostCommentUsecase(),
    private val getCommentListUsecase: GetCommentListUsecase = GetCommentListUsecase(),
    private val refreshTokenUsecase: RefreshTokenUsecase = RefreshTokenUsecase()
) : ViewModel() {
    private val _isLike: MutableLiveData<Boolean> = MutableLiveData()
    val isLike: LiveData<Boolean>
        get() = _isLike

    val commentList : ArrayList<CommentInfo> = ArrayList()
    val commentLoad : SingleLiveEvent<Void> = SingleLiveEvent()

    init {
        _isLike.value = false
    }

    fun clickLikeBtn(boardId: Long) {
        Log.d("likeState", isLike.value.toString())
        Log.d("likeState", boardId.toString())
        Log.d("likeState", SharedPreferenceManager.getStringPref(ConstValue.CONST_ACCESS_TOKEN))
        if (isLike.value == true) {
            recommendBoardUsecase.recommendBoardCancel(boardId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isLike.value = false
                    SharedPreferenceManager.setPref(boardId.toString(), false)
                }, {
                    refreshToken()
                    Log.d("Detail", it.localizedMessage)
                })
        } else {
            recommendBoardUsecase.recommendBoard(boardId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isLike.value = true
                    SharedPreferenceManager.setPref(boardId.toString(), true)
                }, {
                    refreshToken()
                    Log.d("Detail", it.localizedMessage)
                })
        }
    }

    fun getComment(boardId : Long) {
        getCommentListUsecase.getCommentList(boardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                commentList.addAll(it.commentsInfo)
                commentLoad.call()
            }, {
                Log.d("Comment", it.localizedMessage)
            })
    }

    fun postComment(boardId : Long, content: String) {
        postCommentUsecase.postComment(boardId, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                commentList.add(it.commentInfo)
                commentLoad.call()
            }, {
                Log.d("Comment", it.localizedMessage)
            })
    }

    fun refreshToken() {
        if (!(SharedPreferenceManager.getStringPref(ConstValue.CONST_REFRESH_TOKEN).isNullOrEmpty())) {
            refreshTokenUsecase.refreshToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    SharedPreferenceManager.setPref(ConstValue.CONST_ACCESS_TOKEN, it.token)
                },{
                    Log.d("RefreshToken", it.localizedMessage)
                })
        }

    }

    fun setLikeState(state: Boolean) {
        _isLike.value = state
    }
}
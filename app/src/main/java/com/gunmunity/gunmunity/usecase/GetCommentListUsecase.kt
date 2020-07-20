package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.GetCommentsResponse
import com.gunmunity.gunmunity.network.CommentService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCommentListUsecase {
    private val commentService: CommentService = RetrofitManager.create(CommentService::class.java)

    fun getCommentList(boardId : Long): Single<GetCommentsResponse> {
        return commentService.getCommentList(boardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.CreateCommentResponse
import com.gunmunity.gunmunity.network.CommentService
import com.gunmunity.gunmunity.util.ConstValue
import com.gunmunity.gunmunity.util.RetrofitManager
import com.gunmunity.gunmunity.util.SharedPreferenceManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostCommentUsecase {
    private val commentService: CommentService = RetrofitManager.create(CommentService::class.java)

    fun postComment(boardId : Long, content: String): Single<CreateCommentResponse> {
        return commentService.postComment(
            SharedPreferenceManager.getStringPref(ConstValue.CONST_ACCESS_TOKEN), boardId, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
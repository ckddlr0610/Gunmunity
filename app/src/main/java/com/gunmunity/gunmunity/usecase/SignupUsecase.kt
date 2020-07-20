package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.SignupRequest
import com.gunmunity.gunmunity.model.SignupResponse
import com.gunmunity.gunmunity.network.LoginService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupUsecase {
    private val signupService: LoginService = RetrofitManager.create(LoginService::class.java)

    fun doSignup(email: String, hassedPassword: String, nickname: String): Single<SignupResponse> {
        val request: SignupRequest = SignupRequest(email, hassedPassword, nickname)
        return signupService.registerAccount(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
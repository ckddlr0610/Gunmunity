package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.LoginRequest
import com.gunmunity.gunmunity.model.LoginResponse
import com.gunmunity.gunmunity.network.LoginService
import com.gunmunity.gunmunity.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginUsecase {
    private val loginService : LoginService = RetrofitManager.create(LoginService::class.java)

    fun doLogin(email : String ,hassedPassword : String) : Single<LoginResponse> {
        val request : LoginRequest = LoginRequest(email, hassedPassword)
        return loginService.isRegisteredAccount(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
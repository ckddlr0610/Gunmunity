package com.example.gunmunity.usecase

import com.example.gunmunity.model.LoginRequest
import com.example.gunmunity.model.LoginResponse
import com.example.gunmunity.network.LoginService
import com.example.gunmunity.util.RetrofitManager
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
package com.gunmunity.gunmunity.usecase

import com.gunmunity.gunmunity.model.entity.AccessToken
import com.gunmunity.gunmunity.network.LoginService
import com.gunmunity.gunmunity.util.ConstValue
import com.gunmunity.gunmunity.util.RetrofitManager
import com.gunmunity.gunmunity.util.SharedPreferenceManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RefreshTokenUsecase {
    private val loginService: LoginService = RetrofitManager.create(LoginService::class.java)

    fun refreshToken(): Single<AccessToken> {
        return loginService.getRefreshToken(
            SharedPreferenceManager.getStringPref(ConstValue.CONST_REFRESH_TOKEN))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
package com.example.gunmunity.presentation.login_signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gunmunity.usecase.LoginUsecase
import com.example.gunmunity.util.SingleLiveEvent
import com.example.gunmunity.util.sha256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

public class LoginViewModel() : ViewModel() {
    private val loginUsecase: LoginUsecase = LoginUsecase()
    val loginSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doLogin(email : String, password : String) {
        val hashedPassword = password.sha256()
        loginUsecase.doLogin(email, hashedPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginSuccess.call()
            }, {
                Log.d("Login", it.localizedMessage)
            })
    }
}
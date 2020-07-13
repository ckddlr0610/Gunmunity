package com.example.gunmunity.presentation.login_signup

import androidx.lifecycle.ViewModel
import com.example.gunmunity.usecase.SignupUsecase
import com.example.gunmunity.util.SingleLiveEvent
import com.example.gunmunity.util.sha256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

public class SignupViewModel() : ViewModel() {
    private val signupUsecase: SignupUsecase = SignupUsecase()
    val signupSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doSignup(email : String, password : String, nickname: String) {
        val hashedPassword = password.sha256()
        signupUsecase.doSignup(email, hashedPassword, nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                signupSuccess.call()
            }, {

            })
    }
}
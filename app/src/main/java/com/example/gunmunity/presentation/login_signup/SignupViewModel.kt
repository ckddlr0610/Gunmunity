package com.example.gunmunity.presentation.login_signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.gunmunity.usecase.SignupUsecase
import com.example.gunmunity.util.SingleLiveEvent
import com.example.gunmunity.util.sha256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupViewModel : ViewModel() {
    private val signupUsecase: SignupUsecase = SignupUsecase()
    val signupSuccess : SingleLiveEvent<Void> = SingleLiveEvent()
    val emailFailed : SingleLiveEvent<Void> = SingleLiveEvent()
    val passwordFailed : SingleLiveEvent<Void> = SingleLiveEvent()
    val signupFailed : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doSignup(email : String, password : String, nickname: String) {
        val emailRegExp = "^[a-zA-Z0-9._%^-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        val matchResult = emailRegExp.matches(email)
        if (!matchResult) {
            emailFailed.call()
        } else {
            val passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$".toRegex()
            val passwordMatchResult = passwordRegExp.matches(password)
            if (!passwordMatchResult) {
                passwordFailed.call()
            } else {
                val hashedPassword = password.sha256()
                Log.d("Signup","${hashedPassword}")
                signupUsecase.doSignup(email, hashedPassword, nickname)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("Signup","Success Signup")
                        Log.d("Signup","${it.userInfo}")
                        signupSuccess.call()
                    }, {
                        signupFailed.call()
                        Log.d("SignupFailed",it.localizedMessage)
                    })
            }
        }
    }
}
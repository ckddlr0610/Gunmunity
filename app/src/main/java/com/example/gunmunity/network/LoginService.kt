package com.example.gunmunity.network

import com.example.gunmunity.model.LoginRequest
import com.example.gunmunity.model.LoginResponse
import com.example.gunmunity.model.SignupRequest
import com.example.gunmunity.model.SignupResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/v1/auth/sign-in")
    fun isRegisteredAccount(@Body loginRequest : LoginRequest) : Single<LoginResponse>

    @POST("/v1/auth/sign-up")
    fun registerAccount(@Body signupRequest: SignupRequest) : Single<SignupResponse>
}
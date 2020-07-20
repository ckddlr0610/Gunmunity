package com.gunmunity.gunmunity.network

import com.gunmunity.gunmunity.model.LoginRequest
import com.gunmunity.gunmunity.model.LoginResponse
import com.gunmunity.gunmunity.model.SignupRequest
import com.gunmunity.gunmunity.model.SignupResponse
import com.gunmunity.gunmunity.model.entity.AccessToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("/v1/auth/sign-in")
    fun isRegisteredAccount(@Body loginRequest : LoginRequest) : Single<LoginResponse>

    @POST("/v1/auth/sign-up")
    fun registerAccount(@Body signupRequest: SignupRequest) : Single<SignupResponse>

    @GET("/v1/auth/access-token")
    fun getRefreshToken(@Header("refreshToken") refreshToken: String) : Single<AccessToken>
}
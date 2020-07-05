package com.example.gunmunity.model

import com.example.gunmunity.model.entity.AccessToken
import com.example.gunmunity.model.entity.UserInfo

data class LoginResponse (
    private val accessToken : AccessToken,
    private val refreshToken : String,
    private val userInfo : UserInfo
)
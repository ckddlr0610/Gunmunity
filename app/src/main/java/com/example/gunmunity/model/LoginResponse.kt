package com.example.gunmunity.model

import com.example.gunmunity.model.entity.AccessToken
import com.example.gunmunity.model.entity.UserInfo

data class LoginResponse (
    val accessToken : AccessToken,
    val refreshToken : String,
    val userInfo : UserInfo
)
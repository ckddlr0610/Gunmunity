package com.gunmunity.gunmunity.model

import com.gunmunity.gunmunity.model.entity.AccessToken
import com.gunmunity.gunmunity.model.entity.UserInfo

data class LoginResponse (
    val accessToken : AccessToken,
    val refreshToken : String,
    val userInfo : UserInfo
)
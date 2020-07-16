package com.gunmunity.gunmunity.model

import com.gunmunity.gunmunity.model.entity.AccessToken
import com.gunmunity.gunmunity.model.entity.UserInfo

data class SignupResponse (
    private val accessToken : AccessToken,
    private val refreshToken : String,
    private val userInfo: UserInfo
)
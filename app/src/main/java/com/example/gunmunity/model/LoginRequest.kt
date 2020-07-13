package com.example.gunmunity.model

data class LoginRequest (
    private val email : String,
    private val hashedPassword : String
)
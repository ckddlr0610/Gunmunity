package com.example.gunmunity.model

data class SignupRequest (
    private val email : String,
    private val hashedPassword : String,
    private val nickname : String
)
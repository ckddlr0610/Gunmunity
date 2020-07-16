package com.gunmunity.gunmunity.util

import java.security.MessageDigest

class HashingUtil {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}

fun String.md5(): String {
    return HashingUtil().hashString(this, "MD5")
}

fun String.sha256(): String {
    return HashingUtil().hashString(this, "SHA-256")
}
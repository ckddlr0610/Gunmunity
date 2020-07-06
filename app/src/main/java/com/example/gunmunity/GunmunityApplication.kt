package com.example.gunmunity

import android.app.Application
import android.content.Context

class GunmunityApplication : Application() {
    companion object {
        private const val TAG: String = "Gunmunity"
        private const val PRINT_STACK_COUNT: Int = 5

        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
package com.gunmunity.gunmunity.presentation.login_signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.gunmunity.gunmunity.R
import com.gunmunity.gunmunity.presentation.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navController = findNavController(R.id.login_nav_host_fragment)

        navController.setGraph(R.navigation.login_navigation)
    }

    fun startMainActivity() {
        MainActivity.start(this)
    }
}
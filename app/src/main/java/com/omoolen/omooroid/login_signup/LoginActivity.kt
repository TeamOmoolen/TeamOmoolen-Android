package com.omoolen.omooroid.login_signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.omoolen.omooroid.R

class LoginActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_VirtualReality_SNS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}
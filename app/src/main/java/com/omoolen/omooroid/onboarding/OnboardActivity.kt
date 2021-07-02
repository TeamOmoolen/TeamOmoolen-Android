package com.omoolen.omooroid.onboarding

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.OneHomeFragment
import com.omoolen.omooroid.onboarding.fragments.four.FourOnboardFragment

class OnboardActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private val fragmentOnboardFour by lazy { FourOnboardFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        initNavController()
        changeFragment(fragmentOnboardFour)
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_nav_fragment_onboard) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun changeFragment(fragment: Fragment) {
        Log.d("fragmentChangd", fragment.toString())
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.host_nav_fragment_onboard, fragment)
            .commit()
    }
}
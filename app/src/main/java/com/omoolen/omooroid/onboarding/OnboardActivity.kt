package com.omoolen.omooroid.onboarding

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.omoolen.omooroid.R
import com.omoolen.omooroid.onboarding.fragments.four.FourOnboardFragment
import com.omoolen.omooroid.onboarding.fragments.one.OneOnboardFragment
import com.omoolen.omooroid.onboarding.fragments.three.ThreeOnboardFragment
import com.omoolen.omooroid.onboarding.fragments.two.TwoOnboardFragment


class OnboardActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private val fragmentOnboardOne by lazy { OneOnboardFragment() }
    private val fragmentOnboardTwo by lazy { TwoOnboardFragment() }
    private val fragmentOnboardThree by lazy { ThreeOnboardFragment() }
    private val fragmentOnboardFour by lazy { FourOnboardFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        initNavController()
        changeFragment(fragmentOnboardOne)
        startAnimation(0,25)
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

    private fun startAnimation(start:Int,end:Int) {
        val mProgressBar = findViewById<View>(R.id.pb_loading) as ProgressBar
        val progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", start, end)
        progressAnimator.duration = 300
        progressAnimator.interpolator = LinearInterpolator()
        progressAnimator.start()
    }
}
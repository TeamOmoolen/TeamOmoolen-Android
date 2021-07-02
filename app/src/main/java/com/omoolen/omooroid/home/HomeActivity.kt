package com.omoolen.omooroid.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivityHomeBinding
import com.omoolen.omooroid.home.fragments.five.FiveHomeFragment
import com.omoolen.omooroid.home.fragments.four.FourHomeFragment
import com.omoolen.omooroid.home.fragments.one.OneHomeFragment
import com.omoolen.omooroid.home.fragments.three.ThreeHomeFragment
import com.omoolen.omooroid.home.fragments.two.TwoHomeFragment

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
//    private val viewModel: HomeViewModel by viewModels() //위임초기화

    private val fragmentHomeOne by lazy { OneHomeFragment() }
    private val fragmentHomeTwo by lazy { TwoHomeFragment() }
    private val fragmentHomeThree by lazy { ThreeHomeFragment() }
    private val fragmentHomeFour by lazy { FourHomeFragment() }
    private val fragmentHomeFive by lazy { FiveHomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_home) as NavHostFragment
        changeFragment(fragmentHomeOne)
        binding.bnvMain.setOnNavigationItemSelectedListener(this)
    }

    private fun changeFragment(fragment: Fragment) {
        Log.d("fragmentChangd", fragment.toString())
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_home, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                changeFragment(fragmentHomeOne)
                return true
            }
            R.id.locationFragment -> {
                changeFragment(fragmentHomeTwo)
                return true
            }
            R.id.settingFragment -> {
                changeFragment(fragmentHomeThree)
                return true
            }
            R.id.fourFragment -> {
                changeFragment(fragmentHomeFour)
                return true
            }
            R.id.fiveFragment -> {
                changeFragment(fragmentHomeFive)
                return true
            }
            else -> {
                return false
            }
        }
    }

}
package com.example.practices.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.practices.R
import com.example.practices.databinding.ActivityHomeBinding
import android.view.MenuItem
import com.example.practices.home.fragments.one.OneFragment
import com.example.practices.home.fragments.three.ThreeFragment
import com.example.practices.home.fragments.two.TwoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity :AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener{
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
//    private val viewModel: HomeViewModel by viewModels() //위임초기화

    private val fragmentOne by lazy { OneFragment() }
    private val fragmentTwo by lazy { TwoFragment() }
    private val fragmentThree by lazy { ThreeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_home) as NavHostFragment
        changeFragment(fragmentOne)
        binding.bnvMain.setOnNavigationItemSelectedListener(this)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_home, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFragment -> {
                changeFragment(fragmentOne)
                return true
            }
            R.id.locationFragment -> {
                changeFragment(fragmentTwo)
                return true
            }
            R.id.settingFragment -> {
                changeFragment(fragmentThree)
                return true
            }
            else -> {
                return false
            }
        }
    }

}
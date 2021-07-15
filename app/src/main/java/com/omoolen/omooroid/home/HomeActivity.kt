package com.omoolen.omooroid.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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

//  private val viewModel: HomeViewModel by viewModels() //위임초기화

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
        /*binding.viewPagerHome.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerHome.registerOnPageChangeCallback(ViewPagerPageChangeCallback())

        binding.bnvMain.setOnNavigationItemSelectedListener(this)*/
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_home) as NavHostFragment
        //changeFragment(fragmentHomeOne)
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
            R.id.findFragment -> {
                changeFragment(fragmentHomeTwo)
                return true
            }
            R.id.eventFragment -> {
                changeFragment(fragmentHomeThree)
                return true
            }
            R.id.tipFragment -> {
                changeFragment(fragmentHomeFour)
                return true
            }
            R.id.mypageFragment -> {
                changeFragment(fragmentHomeFive)
                return true
            }
            else -> {
                return false
            }
        }
    }

    fun setBottomChecked(index:Int){
        binding.bnvMain.menu.getItem(index).isChecked = true
    }


   /* private inner class ViewPagerAdapter(fm: FragmentManager, lc: Lifecycle): FragmentStateAdapter(fm, lc) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> OneHomeFragment()
                1 -> TwoHomeFragment()
                2 -> ThreeHomeFragment()
                3 -> FourHomeFragment()
                4 -> FiveHomeFragment()
                else -> OneHomeFragment()
            }
        }
    }

    private inner class ViewPagerPageChangeCallback : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            binding.bnvMain.selectedItemId = when(position) {
                0 -> R.id.homeFragment
                1 -> R.id.locationFragment
                2 -> R.id.settingFragment
                3 -> R.id.fourFragment
                4 -> R.id.fiveFragment
                else -> error("No id")
            }
        }
    }
*/

}
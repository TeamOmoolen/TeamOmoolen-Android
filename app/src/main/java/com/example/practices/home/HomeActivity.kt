package com.example.practices.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.practices.R
import com.example.practices.databinding.ActivityHomeBinding
import com.example.practices.home.fragments.one.OneFragment
import com.example.practices.home.fragments.three.ThreeFragment
import com.example.practices.home.fragments.two.TwoFragment
import com.example.practices.util.HashKey
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging

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

        firebasePushalarmCallback()
        firebaseGetToken()

        //앱 해시키 가져오기
        getHashKey()
    }

    private fun getHashKey(){
        val hashKey = HashKey()
        hashKey.getHashKey(this)
    }

    private fun firebasePushalarmCallback(){
        var intent = getIntent()
        if (intent != null) { //푸시알림을 선택해서 실행한것이 아닌경우 예외처리
            val notificationData = intent.getStringExtra("test")
            if (notificationData != null) {
                changeFragment(fragmentTwo)//fragment 전환
                binding.bnvMain.selectedItemId = R.id.locationFragment //bottom navigation 선택 바꾸기
            }
        }
    }
    private fun firebaseGetToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "FirebasePractice.TAG",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            } else {
                val token = task.result
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("fbPractice.Success", msg)
            }
        })
    }


    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_home) as NavHostFragment
        changeFragment(fragmentOne)
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
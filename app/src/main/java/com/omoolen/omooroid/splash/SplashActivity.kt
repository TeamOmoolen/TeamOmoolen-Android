package com.omoolen.omooroid.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.home.fragments.one.OneHomeFragment
import com.omoolen.omooroid.login_signup.LoginActivity
import com.omoolen.omooroid.util.SharedPreferenceToken
import java.util.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //token 값 검사

        //Timer().schedule(1000){
            if(hasToken()){
                Log.d("*******SPLASH", hasToken().toString())
//                val fragment1 = OneHomeFragment()
//                supportFragmentManager.beginTransaction().add(R.id.nav_host_home, fragment1).commit()

                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Log.d("*******SPLASH", hasToken().toString())
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        //}
    }

    //로그인상태 검사
    private fun hasToken(): Boolean {
        var userToken = SharedPreferenceToken.getSettingItem(this,"USER_TOKEN")
        Log.d("*******SPLASH", userToken.toString())

        return userToken != null
    }

}
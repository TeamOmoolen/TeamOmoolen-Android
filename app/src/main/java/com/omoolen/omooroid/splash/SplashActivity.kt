package com.omoolen.omooroid.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.omoolen.omooroid.home.HomeActivity
import com.omoolen.omooroid.login_signup.LoginActivity
import com.omoolen.omooroid.util.SharedPreferenceToken
import java.util.*
import kotlin.concurrent.schedule


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //token 값 검사

        Timer().schedule(1000){
            if(hasToken()){
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    //로그인상태 검사
    private fun hasToken(): Boolean {
        var userToken = SharedPreferenceToken.getSettingItem(this,"USER_TOKEN")
        return userToken != null
    }

}
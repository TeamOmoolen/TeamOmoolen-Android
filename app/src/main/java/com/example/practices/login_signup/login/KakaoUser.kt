package com.example.practices.login_signup.login

import android.util.Log

data class KakaoUser(
    val tokenID:String,
    val userId:String,
    val userEmail:String,
    val nickname:String,
    val profileImage:String,
    val gender:String,
    val age:String
)

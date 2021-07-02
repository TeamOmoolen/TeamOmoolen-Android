package com.omoolen.omooroid.login_signup.login

data class KakaoUser(
    val tokenID: String,
    val userId: String,
    val userEmail: String,
    val nickname: String,
    val profileImage: String,
    val gender: String,
    val age: String
)

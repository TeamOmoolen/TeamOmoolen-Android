package com.omoolen.omooroid.login_signup.login.loginApi

import com.kakao.usermgmt.response.model.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST(".")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
}
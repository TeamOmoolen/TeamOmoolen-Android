package com.omoolen.omooroid.login_signup.login.loginApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object UserClient {
    private const val BASE_URL = "http://ec2-15-165-235-44.ap-northeast-2.compute.amazonaws.com/"

    val getApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(UserService::class.java)
}
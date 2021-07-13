package com.omoolen.omooroid

import com.omoolen.omooroid.home.fragments.one.networkApi.OneService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder_One {
    private const val BASE_URL =""
    private val getApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
   val oneService : OneService = getApi.create(OneService :: class.java)
}
package com.omoolen.omooroid.search.fragment.two.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SearchClient {
    private const val BASE_URL = "http://15.164.251.55:5000/api/main/" //TODO : 수정

    val getApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(SearchService::class.java)
}
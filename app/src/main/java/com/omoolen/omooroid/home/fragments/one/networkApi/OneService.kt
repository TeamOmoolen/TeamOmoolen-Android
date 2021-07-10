package com.omoolen.omooroid.home.fragments.one.networkApi

import io.reactivex.Single
import retrofit2.http.GET

interface OneService {
    @GET("home/One")
    suspend fun getOne() : ResponseOneList
}
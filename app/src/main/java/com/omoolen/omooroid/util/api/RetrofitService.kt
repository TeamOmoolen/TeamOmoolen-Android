package com.omoolen.omooroid.util.api

import com.omoolen.omooroid.search.data.Data
import com.omoolen.omooroid.search.fragment.one.recycle.popular.ResponsePopularInfo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    //키워드 검색
    @GET("api/searchProduct")
    fun getData() : Single<Data>


    @GET("api/searchWindow")
    fun getPopularData() : Call<ResponsePopularInfo>
}
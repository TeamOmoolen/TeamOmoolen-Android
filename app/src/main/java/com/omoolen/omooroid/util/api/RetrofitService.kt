package com.omoolen.omooroid.util.api

import com.omoolen.omooroid.search.data.Data
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {
    //키워드 검색
    @GET("api/searchProduct")
    fun getData() : Single<Data>
}
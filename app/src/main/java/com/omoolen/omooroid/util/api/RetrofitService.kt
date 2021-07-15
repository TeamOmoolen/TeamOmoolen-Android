package com.omoolen.omooroid.util.api

import com.omoolen.omooroid.search.data.Data
import com.omoolen.omooroid.search.data.KeywordSearch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    //키워드 검색
    @GET("api/searchProduct")
    fun getData(
        @Query("keyword") keyword : String
    ) : Single<KeywordSearch>
}
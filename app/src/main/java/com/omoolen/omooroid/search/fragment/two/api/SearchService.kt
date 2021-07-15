package com.omoolen.omooroid.search.fragment.two.api

import io.reactivex.Single
import retrofit2.http.GET

interface SearchService {
    @GET("news") //TODO : 수정
    fun getSearch() : Single<ResponseFilterData>
    fun getData() :Single<Data>
    fun getItem() : Single<Item>
}
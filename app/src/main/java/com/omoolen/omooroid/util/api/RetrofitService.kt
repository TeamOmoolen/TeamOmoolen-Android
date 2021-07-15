package com.omoolen.omooroid.util.api

import com.omoolen.omooroid.login_signup.login.loginApi.RequestLoginData
import com.omoolen.omooroid.login_signup.login.loginApi.ResponseLoginData
import com.omoolen.omooroid.onboarding.api.RequestOnboardData
import com.omoolen.omooroid.onboarding.api.ResponseOnboardData
import com.omoolen.omooroid.search.data.Data
import com.omoolen.omooroid.search.data.KeywordSearch
import com.omoolen.omooroid.search.fragment.one.recycle.popular.ResponsePopularInfo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    //키워드 검색
    @GET("api/searchProduct")
    fun getData(
        @Query("keyword") keyword : String
    ) : Single<KeywordSearch>

    //인기검색어
    @GET("api/searchWindow")
    fun getPopularData() : Call<ResponsePopularInfo>


    @POST("api/users/saveOnBoardingData")
    fun postOnboard(
        @Body body: RequestOnboardData
    ) : Call<ResponseOnboardData>
}
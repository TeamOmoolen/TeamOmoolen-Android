package com.omoolen.omooroid.util.api

import com.omoolen.omooroid.detail.detailApi.DetailData
import com.omoolen.omooroid.home.fragments.one.networkApi.HomeData
import com.omoolen.omooroid.home.homeApi.SuggestData
import com.omoolen.omooroid.onboarding.api.RequestOnboardData
import com.omoolen.omooroid.onboarding.api.ResponseOnboardData
import com.omoolen.omooroid.search.data.KeywordSearch
import com.omoolen.omooroid.search.fragment.one.recycle.popular.ResponsePopularInfo
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.RequestSearchData
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.ResponseSearchData
import io.reactivex.Single
import okhttp3.internal.http.hasBody
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

    //온보딩 정보 전송
    @POST("api/users/saveOnBoardingData")
    fun postOnboard(
        @Body body: RequestOnboardData
    ) : Call<ResponseOnboardData>

    //홈 정보
    @GET("api/home")
    fun getHomeData() : Single<HomeData>

    //필터 검색
    @POST("api/getFilteredList")
    fun getFilterSearch(
        @Body body: RequestSearchData
    ) : Call<ResponseSearchData>

    //제품 상세정보
    @GET("api/products/{id}")
    fun getDetailData(
        @Path("id") id:String
    ) : Single<DetailData>

    //발견 홈 하단 클릭
    @GET("api/suggest")
    fun getSuggestData() : Single<SuggestData>

    //발견 for you 클릭
    @GET("api/suggest/foryou")
    fun getForyou(
        @Query("page") page : String,
        @Query("sort") sort : String,
        @Query("order") order : String
    ) : Single<KeywordSearch>

}
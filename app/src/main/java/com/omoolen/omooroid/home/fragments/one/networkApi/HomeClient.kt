package com.omoolen.omooroid.home.fragments.one.networkApi

import android.util.Log
import com.omoolen.omooroid.util.GlobalApplication
import com.omoolen.omooroid.util.SharedPreferenceToken
import com.omoolen.omooroid.util.api.RetrofitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object HomeClient{
    private const val BASE_URL = "http://ec2-15-165-235-44.ap-northeast-2.compute.amazonaws.com/"

    val getApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptor()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(RetrofitService::class.java)

    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            var userToken = SharedPreferenceToken.getSettingItem(GlobalApplication.ApplicationContext(),"USER_TOKEN")
            Log.d("@#@#@#@@#",userToken.toString())
            val newRequest = request().newBuilder()
                .addHeader("accesstoken",
                    userToken.toString())
                .build()

            proceed(newRequest)
        }
    }
}
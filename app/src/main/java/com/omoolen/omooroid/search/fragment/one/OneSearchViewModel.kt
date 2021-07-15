package com.omoolen.omooroid.search.fragment.one

import android.util.Log
import androidx.lifecycle.*
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.popular.ResponsePopularInfo
import com.omoolen.omooroid.util.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class OneSearchViewModel() : ViewModel() {


    //인기 검색어

    private val _responsePopular= MutableLiveData<List<PopularInfo>>()
    val responsePopular : LiveData<List<PopularInfo>>
        get() = _responsePopular


    fun getPopularList() {
        val call = RetrofitClient.getApi.getPopularData()
        call.enqueue(object : Callback<ResponsePopularInfo> {
            override fun onResponse(
                call: Call<ResponsePopularInfo>, response: Response<ResponsePopularInfo>
            ) {
                if (response.isSuccessful) {

                    val list = mutableListOf<PopularInfo>()
                    val data = response.body()?.data

                    if (data != null) {
                        var rank : Int = 1
                        for (i in data.indices) {
                            Log.d("popular", "$rank, ${data[i].name}")
                            list.add(
                                PopularInfo(
                                    rank++,
                                    data[i].name,
                                    data[i].id
                                )
                            )
                        }
                    }
                    _responsePopular.value = list
                }
            }

            override fun onFailure(call: Call<ResponsePopularInfo>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

    //TODO : 상세페이지로 이동
    fun intentToDetail(){

    }


}
package com.omoolen.omooroid.search.fragment.one

import android.util.Log
import androidx.lifecycle.*
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.popular.ResponsePopularInfo
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class OneSearchViewModel() : ViewModel() {

    //인기 검색어
    private val _popularSuccess= MutableLiveData<Boolean>()
    val popularSuccess : LiveData<Boolean>
        get() = _popularSuccess

    val popularList = ListLiveData<PopularInfo>()

    fun getPopularList() {
        val call = RetrofitClient.getApi.getPopularData()
        call.enqueue(object : Callback<ResponsePopularInfo> {
            override fun onResponse(
                call: Call<ResponsePopularInfo>, response: Response<ResponsePopularInfo>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    if (data != null) {
                        var rank : Int = 1
                        for (i in data.indices) {
                            Log.d("popular", "$rank, ${data[i].name}")
                            popularList.add( PopularInfo(rank++, data[i].name, data[i].id))
                        }
                    }
                    _popularSuccess.value =  true
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
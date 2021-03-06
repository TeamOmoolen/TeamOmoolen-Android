package com.omoolen.omooroid.search.search_result

//import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.RequestSearchData
import com.omoolen.omooroid.search.fragment.two.filterSearchApi.ResponseSearchData

import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class SearchResultViewModel(application: Application) : AndroidViewModel(application) {

    private val _searchResultList = MutableLiveData<List<SearchResultInfo>>()
    val searchResultList = ListLiveData<Item>()
    var totalItem = MutableLiveData<Int>()
    var mTotalPages : Int = 0



    //@SuppressLint("CheckResult")
    fun getSearch(keyword: String) {
        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getData(keyword = keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({keyword ->
                searchResultList.clear()
                Log.d("RETROFIT_ENTER", keyword.data.totalPage.toString())
                //Log.d("RETROFIT_ENTER",keyword.data.items[0].brand)

                keyword.data.items.forEach{
                    searchResultList.add(
                        Item(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,
                        it.diameter,it.id,it.imageList,it.name,
                        it.otherColorList,it.pieces,it.price)
                    )
                }
                mTotalPages = keyword.data.totalPage
                totalItem.value = keyword.data.items.size

                Log.d("*COLOR_ITEM_SIZE", "$totalItem")

            },{e ->
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }




    /* fun setSearchResultAdapter() : SearchResultListAdapter{
         searchResultListAdapter = SearchResultListAdapter()
         searchResultListAdapter.searchResultList.addAll(
             listOf<SearchResultInfo>(
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 ),
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 ),
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 ),
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 ),
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 ),
                 SearchResultInfo(
                     R.drawable.rectangle_3410, R.drawable.img_color_a,
                     "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                     18000, mutableListOf(
                         "#c4c4c4", "#ffca6c",
                         "#597838", "#9249f6"
                     )
                 )

             )

         )
         return  searchResultListAdapter
     }*/

}
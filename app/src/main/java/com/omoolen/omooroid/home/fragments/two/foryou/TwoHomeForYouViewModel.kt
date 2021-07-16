package com.omoolen.omooroid.home.fragments.two.foryou

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo
import com.omoolen.omooroid.home.fragments.two.api.Item

import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TwoHomeForYouViewModel : ViewModel() {
    private val _forYouList = MutableLiveData<List<RecommendInfo>>()
    val foryouList: LiveData<List<RecommendInfo>>
        get() = _forYouList

    val itemList = ListLiveData<Item>()
    val totalPage = MutableLiveData<Int>()

    //@SuppressLint("CheckResult")
    fun getForyou(page:Int,sort:String,order:String) {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getForyou(page = page,sort = sort,order = order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({each ->
                itemList.clear()
                Log.d("FORYOU",each.message)
                each.data.items.forEach{
                    itemList.add(Item(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                    it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price))
                }
                totalPage.value = each.data.totalPage
                for(i in 0 until itemList.size())
                    Log.d("FORYOU",itemList[i].price.toString())

            },{e ->
                Log.d("FORYOU","에러")
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }


    fun setForYouList() {
        _forYouList.value = mutableListOf(
            RecommendInfo(1,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(2,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                17000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(3,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                16000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(4,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                19000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(5,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                14000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(6,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                12000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            )
        )
    }
}
class PriceComparator {
    companion object : Comparator<RecommendInfo> {
        override fun compare(p0: RecommendInfo?, p1: RecommendInfo?): Int {
            return p0!!.price.compareTo(p1!!.price)
        }
    }
}
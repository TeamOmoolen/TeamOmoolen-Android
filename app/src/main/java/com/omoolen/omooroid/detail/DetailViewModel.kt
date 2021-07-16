package com.omoolen.omooroid.detail

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.detail.detailApi.Data
import com.omoolen.omooroid.detail.detailApi.DetailData
import com.omoolen.omooroid.detail.detailApi.Popular
import com.omoolen.omooroid.detail.detailApi.Suggest
import com.omoolen.omooroid.detail.popular.DetailNewInfo
import com.omoolen.omooroid.detail.recommend.DetailRecommendInfo
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val popularList = ListLiveData<Popular>()
    val suggestList = ListLiveData<Suggest>()

    val detailList = ListLiveData<Item>()
    val detail = MutableLiveData<Data>()
//    var brand: String,
//    val changeCycleMaximum: Int,
//    val changeCycleMinimum: Int,
//    val color: String,
//    val diameter: Double,
//    val function: String,
//    val imageURL: List<String>,
//    val material: String,
//    val name: String,
//    val otherColorList: List<String>,
//    val popularList: List<Popular>,
//    val price: Int,
//    val suggestList: List<Suggest>
    @SuppressLint("CheckResult")
    fun getDetailData(id:String){
    Log.d("SERVER_DETAIL","들어옴")
        RetrofitClient.getApi.getDetailData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({detailData ->
                //detailList.clear()
                val detail = Data(detailData.data.brand,detailData.data.changeCycleMaximum,detailData.data.changeCycleMinimum,
                detailData.data.color,detailData.data.diameter,detailData.data.function,
                detailData.data.imageURL,detailData.data.material,detailData.data.name,
                detailData.data.otherColorList,detailData.data.popularList,detailData.data.price,detailData.data.suggestList)

                Log.d("SERVER_DETAIL","$detail.color, $detail.brand, ${detail.changeCycleMinimum}, ${detail.changeCycleMinimum}")
            },{e ->
                Log.d("SERVER_DETAIL","에러")
                println(e.toString())
            })
    Log.d("SERVER_DETAIL","끝")
}



    private val _detailImageList = MutableLiveData<List<DetailInfo>>()
    val detailImageList: LiveData<List<DetailInfo>>
        get() = _detailImageList

    private val _detailRecommendList = MutableLiveData<List<DetailRecommendInfo>>()
    val detailRecommendList: LiveData<List<DetailRecommendInfo>>
        get() = _detailRecommendList

    private val _detailNewList = MutableLiveData<List<DetailNewInfo>>()
    val detailNewList: LiveData<List<DetailNewInfo>>
        get() = _detailNewList

    private val _detailLensColorList = MutableLiveData<List<String>>()
    val detailLensColorList: LiveData<List<String>>
        get() = _detailLensColorList

    fun setDetailImageList() {
        _detailImageList.value = mutableListOf(
            DetailInfo(
                R.drawable.rectangle_3607
            ),
            DetailInfo(
                R.drawable.rectangle_3607
            ),
            DetailInfo(
                R.drawable.rectangle_3607
            )
        )
    }

    fun setDetailRecommendList() {
        _detailRecommendList.value = mutableListOf(
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailRecommendInfo(
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            )
        )
    }

    fun setDetailNewList() {
        _detailNewList.value = mutableListOf(
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            ),
            DetailNewInfo(
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf(
                    "#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6"
                )
            )
        )
    }

    fun setDetailLensColorList() {
        _detailLensColorList.value = mutableListOf(
            "#b88448", "#568d4d", "#302e68"
        )
    }

}
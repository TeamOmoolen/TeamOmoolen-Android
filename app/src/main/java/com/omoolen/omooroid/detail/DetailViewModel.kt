package com.omoolen.omooroid.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.detail.popular.DetailNewInfo
import com.omoolen.omooroid.detail.popular.DetailNewListAdapter
import com.omoolen.omooroid.detail.recommend.DetailRecommendInfo
import com.omoolen.omooroid.detail.recommend.DetailRecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.event.EventInfo

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = getApplication<Application>().applicationContext
    private lateinit var detailNewListAdapter: DetailNewListAdapter
    private lateinit var detailRecommendListAdapter: DetailRecommendListAdapter

    //Observe위해 livedata선언
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
            DetailInfo(1,
                R.drawable.rectangle_3607
            ),
            DetailInfo(2,
                R.drawable.rectangle_3607
            ),
            DetailInfo(3,
                R.drawable.rectangle_3607
            )
        )
    }



    fun setDetailNewListAdapter() : DetailNewListAdapter{
        detailNewListAdapter = DetailNewListAdapter()
        detailNewListAdapter.detailNewList.addAll(
            listOf<DetailNewInfo>(
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
        )
        return detailNewListAdapter
    }


    fun setDetailRecommendAdapter():DetailRecommendListAdapter {
        detailRecommendListAdapter = DetailRecommendListAdapter()
        detailRecommendListAdapter.detailRecommendList.addAll(
            listOf<DetailRecommendInfo>(
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
        )
        return detailRecommendListAdapter
    }


    fun setDetailLensColorList() {
        _detailLensColorList.value = mutableListOf(
            "#b88448", "#568d4d", "#302e68"
        )
    }

}
package com.omoolen.omooroid.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.detail.popular.DetailNewInfo
import com.omoolen.omooroid.detail.recommend.DetailRecommendInfo

class DetailViewModel(application: Application) : AndroidViewModel(application) {

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
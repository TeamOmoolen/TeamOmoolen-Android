package com.omoolen.omooroid.home.fragments.two.newItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo

class TwoHomeNewViewModel : ViewModel() {
    private val _newList = MutableLiveData<List<RecommendInfo>>()
    val newList: LiveData<List<RecommendInfo>>
        get() = _newList

    fun setNewList() {
        _newList.value = mutableListOf(
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
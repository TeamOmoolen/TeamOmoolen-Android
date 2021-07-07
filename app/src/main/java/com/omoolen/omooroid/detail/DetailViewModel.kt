package com.omoolen.omooroid.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.detail.DetailInfo

class DetailViewModel (application: Application) : AndroidViewModel(application) {
    private val _detailImageList = MutableLiveData<List<DetailInfo>>()
    val detailImageList: LiveData<List<DetailInfo>>
        get() = _detailImageList

    fun setDetailImageList() {
        _detailImageList.value = mutableListOf(
            DetailInfo(
                R.drawable.e3
            ),
            DetailInfo(
                R.drawable.e3
            ),
            DetailInfo(
                R.drawable.e3
            )
        )
    }


}
package com.omoolen.omooroid.search.search_result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.detail.recommend.DetailRecommendInfo

class SearchResultViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var searchResultListAdapter: SearchResultListAdapter

    private val _searchResultList = MutableLiveData<List<SearchResultInfo>>()
    val searchResultList: LiveData<List<SearchResultInfo>>
        get() = _searchResultList

    fun setSearchResultAdapter() : SearchResultListAdapter{
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
    }

}
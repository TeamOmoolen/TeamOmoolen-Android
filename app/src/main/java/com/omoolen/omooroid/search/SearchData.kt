package com.omoolen.omooroid.search

import com.omoolen.omooroid.util.ListLiveData

data class SearchData(
    var brand: MutableList<String>,
    var changeCycleRange: MutableList<Int>,
    var color: MutableList<String>,
    var diameter: Int
)
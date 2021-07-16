package com.omoolen.omooroid.search.fragment.two.filterSearchApi

data class RequestSearchData(
    val brand: List<String>,
    val changeCycleRange: List<Int>,
    val color: List<String>,
    val diameter: Int
)
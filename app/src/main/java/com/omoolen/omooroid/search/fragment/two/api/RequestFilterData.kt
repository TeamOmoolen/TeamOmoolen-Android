package com.omoolen.omooroid.search.fragment.two.api

data class RequestFilterData(
    val brand: List<String>,
    val changeCycleRange: List<Int>,
    val color: List<String>,
    val diameter: Int
)
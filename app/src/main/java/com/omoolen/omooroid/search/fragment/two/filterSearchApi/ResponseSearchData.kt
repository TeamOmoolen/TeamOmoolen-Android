package com.omoolen.omooroid.search.fragment.two.filterSearchApi

data class ResponseSearchData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)
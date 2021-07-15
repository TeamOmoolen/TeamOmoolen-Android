package com.omoolen.omooroid.search.search_result

data class SearchResultInfo(
    val imgId1: Int,
    val imgId2: Int,
    val brand: String,
    val name: String,
    val info: String,
    val price: Int,
    val colors: MutableList<String>
)

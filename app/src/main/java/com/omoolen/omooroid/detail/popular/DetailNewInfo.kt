package com.omoolen.omooroid.detail.popular

data class DetailNewInfo(
    val imgId1: Int,
    val imgId2: Int,
    val brand: String,
    val name: String,
    val info: String,
    val price: Int,
    val colors: MutableList<String>
)

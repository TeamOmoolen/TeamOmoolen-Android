package com.omoolen.omooroid.detail.recommend

data class DetailRecommendInfo(
    val imgId1: Int,
    val imgId2: Int,
    val brand: String,
    val name: String,
    val info: String,
    val price: Int,
    val colors: MutableList<String>
)

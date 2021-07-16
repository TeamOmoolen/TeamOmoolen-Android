package com.omoolen.omooroid.home.fragments.one.networkApi

data class RecommendationByUser(
    val brand: String,
    val changeCycleMaximum: Int,
    val changeCycleMinimum: Int,
    val diameter: Double,
    val id: String,
    val imageList: List<String>,
    val name: String,
    val otherColorList: List<Any>,
    val pieces: Int,
    val price: Int
)
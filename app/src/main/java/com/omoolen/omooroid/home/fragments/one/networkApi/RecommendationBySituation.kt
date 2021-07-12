package com.omoolen.omooroid.home.fragments.one.networkApi

data class RecommendationBySituation(
    val brand: String,
    val category: String,
    val changeCycle: Int,
    val color: Int,
    val diameter: Double,
    val id: Int,
    val imageList: List<String>,
    val name: String,
    val otherColorList: List<Int>,
    val pieces: Int,
    val price: Int,
    val releaseDate: String
)
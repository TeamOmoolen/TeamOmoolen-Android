package com.omoolen.omooroid.home.fragments.one.curating

data class FindRecomendationByUser(
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
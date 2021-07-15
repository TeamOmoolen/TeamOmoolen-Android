package com.omoolen.omooroid.search.fragment.two.api

data class Item(
    val brand: String,
    val category: String,
    val changeCycle: Int,
    val changeCycleRange: Int,
    val color: String,
    val diameter: Double,
    val function: String,
    val id: String,
    val imageList: List<String>,
    val material: String,
    val name: String,
    val otherColorList: List<String>,
    val pieces: Int,
    val price: Int,
    val releaseDate: String,
    val searchCount: Int,
    val visionMaximum: Int,
    val visionMinimum: Int
)
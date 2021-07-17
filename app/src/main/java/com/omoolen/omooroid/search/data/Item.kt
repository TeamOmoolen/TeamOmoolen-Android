package com.omoolen.omooroid.search.data

data class Item(
    val brand: String,
    val changeCycleMaximum: Int,
    val changeCycleMinimum: Int,
    val diameter: Double,
    val id: String,
    val imageList: List<String>,
    val name: String,
    val otherColorList: List<String>,
    val pieces: Int,
    val price: Int
)
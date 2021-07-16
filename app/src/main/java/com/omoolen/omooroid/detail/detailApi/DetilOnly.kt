package com.omoolen.omooroid.detail.detailApi

data class DetilOnly(
    var brand: String,
    val changeCycleMaximum: Int,
    var changeCycleMinimum: Int,
    var color: String,
    val diameter: Double,
    val function: String,
    val imageURL: List<String>,
    val material: String,
    val name: String,
    val otherColorList: List<String>,
    val price: Int
)

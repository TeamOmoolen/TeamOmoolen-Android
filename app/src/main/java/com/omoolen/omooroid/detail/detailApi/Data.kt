package com.omoolen.omooroid.detail.detailApi

data class Data(
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
    val popularList: List<Popular>,
    val price: Int,
    val suggestList: List<Suggest>
)
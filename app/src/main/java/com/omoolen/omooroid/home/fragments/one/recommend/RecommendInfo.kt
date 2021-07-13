package com.omoolen.omooroid.home.fragments.one.recommend

data class RecommendInfo(
    val id : Int,
    val imgId1 : Int,
    val imgId2 : Int,
    val brand : String,
    val name : String,
    val info : String,
    val price : Int,
    val colors : MutableList<String>
)



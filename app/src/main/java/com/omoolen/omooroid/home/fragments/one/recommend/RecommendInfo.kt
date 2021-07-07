package com.omoolen.omooroid.home.fragments.one.recommend

// info의 경우 직경과 타입이 각각 올 수 있음. 서버와 이야기 후 윤곽잡기.
data class RecommendInfo(
    val imgId1 : Int,
    val imgId2 : Int,
    val brand : String,
    val name : String,
    val info : String,
    val price : Int,
    val colors : MutableList<String>
)

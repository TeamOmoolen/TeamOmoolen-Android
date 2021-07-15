package com.omoolen.omooroid.search.data

import android.util.Log

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
){
    fun show(){
        Log.d("DATA_ITEM","$brand , $changeCycleMaximum , $changeCycleMinimum , $diameter , $id , " +
                "$imageList , $name , $otherColorList , $pieces , $price")
    }
}
package com.omoolen.omooroid.home.fragments.one.networkApi

data class NewLens(
    val brand1: List<Brand1>,
    val brand2: List<Brand2>,
    val brand3: List<Brand3>
)
data class Brand1(
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
data class Brand2(
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

data class Brand3(
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
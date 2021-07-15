package com.omoolen.omooroid.home.fragments.one.networkApi

data class Product(
    val brand: String,
    val id: String,
    val imageList: List<String>,
    val name: String,
    val price: Int
)
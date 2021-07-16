package com.omoolen.omooroid.detail.detailApi

data class DetailData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)
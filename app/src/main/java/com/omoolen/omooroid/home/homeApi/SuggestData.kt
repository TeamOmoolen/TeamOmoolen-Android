package com.omoolen.omooroid.home.homeApi

data class SuggestData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)
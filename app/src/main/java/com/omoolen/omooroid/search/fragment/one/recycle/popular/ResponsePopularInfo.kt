package com.omoolen.omooroid.search.fragment.one.recycle.popular

import com.google.gson.annotations.SerializedName

data class ResponsePopularInfo(
    @SerializedName("data")
    val `data`: List<Data>,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class Data(
    val id: String,
    val name: String
)
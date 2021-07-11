package com.omoolen.omooroid.home.fragments.one.networkApi

import com.google.gson.annotations.SerializedName

data class ResponseOneList(

    @SerializedName("data")
    val `data`: ResponesOneData,
    val message: String,
    val status: Int,
    val succses: Boolean
)
data class ResponesOneData(

    val deadlineEvent: List<DeadlineEvent>,
    val findRecomendationByUser: List<FindRecomendationByUser>,
    val guides: List<Guide>,
    val lastestEvent: List<LastestEvent>,
    val newLens: NewLens,
    val recommendationBySeason: List<RecommendationBySeason>,
    val recommendationBySituation: List<RecommendationBySituation>,
    val username: String
)
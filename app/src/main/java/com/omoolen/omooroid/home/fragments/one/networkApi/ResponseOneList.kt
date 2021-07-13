package com.omoolen.omooroid.home.fragments.one.networkApi

import com.google.gson.annotations.SerializedName
import com.omoolen.omooroid.home.fragments.one.curating.FindRecomendationByUser
import com.omoolen.omooroid.home.fragments.one.event.DeadlineEvent
import com.omoolen.omooroid.home.fragments.one.event.LastestEvent
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendationBySeason
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.one.tip.Guide

data class ResponseOneList(

    @SerializedName("data")
    val `data`: ResponseOneData,
    val message: String,
    val status: Int,
    val succses: Boolean
)
data class ResponseOneData(

    val deadlineEvent: List<DeadlineEvent>,
    val findRecomendationByUser: List<FindRecomendationByUser>,
    val guides: List<Guide>,
    val lastestEvent: List<LastestEvent>,
    val newLens: NewLens,
    val recommendationBySeason: List<RecommendationBySeason>,
    val recommendationBySituation: List<RecommendationBySituation>,
    val username: String
)
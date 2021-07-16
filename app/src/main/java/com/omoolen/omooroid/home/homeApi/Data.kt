package com.omoolen.omooroid.home.homeApi

import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation

data class Data(
    val season: String,
    val situation: String,
    val suggestForNew: List<RecommendationBySituation>,
    val suggestForSeason: List<RecommendationBySituation>,
    val suggestForSituation: List<RecommendationBySituation>,
    val suggestForYou: List<RecommendationBySituation>
)
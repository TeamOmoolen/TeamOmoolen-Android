package com.omoolen.omooroid.home.fragments.one.networkApi

data class Data(
    val deadlineEvent: List<DeadlineEvent>,
    val guides: Guides,
    val lastestEvent: List<LastestEvent>,
    val newLens: NewLens,
    val recommendationBySeason: List<RecommendationBySeason>,
    val recommendationBySituation: List<RecommendationBySituation>,
    val recommendationByUser: List<RecommendationByUser>,
    val season: String,
    val situation: String,
    val username: String
)
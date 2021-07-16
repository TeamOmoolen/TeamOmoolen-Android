package com.omoolen.omooroid.home.homeApi

data class Data(
    val season: String,
    val situation: String,
    val suggestForNew: List<SuggestForNew>,
    val suggestForSeason: List<SuggestForSeason>,
    val suggestForSituation: List<Any>,
    val suggestForYou: List<Any>
)
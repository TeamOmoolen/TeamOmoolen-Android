package com.omoolen.omooroid.onboarding.api

data class WantedLens(
    val category: List<String>,
    val changeCycleRange: List<Int>,
    val color: ArrayList<String>,
    val function: String
)
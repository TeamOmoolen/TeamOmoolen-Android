package com.omoolen.omooroid.onboarding.api

data class RequestOnboardData(
    val age: Int,
    val gender: String,
    val suitedLens: SuitedLens,
    val wantedLens: WantedLens,
    val wearTime: String
)
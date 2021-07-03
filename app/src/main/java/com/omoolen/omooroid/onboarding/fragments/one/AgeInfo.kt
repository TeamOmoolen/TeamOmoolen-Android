package com.omoolen.omooroid.onboarding.fragments.one

data class AgeInfo(
    val age: String
) {
    fun getName(): String? {
        return age
    }
}

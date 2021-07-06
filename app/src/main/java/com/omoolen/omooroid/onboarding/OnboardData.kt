package com.omoolen.omooroid.onboarding

data class OnboardData(
    var _gender: Int = -1,
    var _age: Int = -1,
    var _what: ArrayList<Int> = arrayListOf(),
    var _color: ArrayList<Int> = arrayListOf(),
    var _effect: Int = -1,
    var _period: Int = -1,
    var _brand: Int = -1,
    var _name: String,
    var _when: Int = -1
)

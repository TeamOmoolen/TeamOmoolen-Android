package com.omoolen.omooroid.onboarding

import com.omoolen.omooroid.util.ListLiveData

data class OnboardData(
    var _gender: Int = -1,
    var _age: Int = -1,
    var _what: ListLiveData<Int>?,
    var _color: ListLiveData<Int>?,
    var _effect: Int = -1,
    var _period: Int = -1,
    var _brand: Int = -1,
    var _name: String,
    var _when: Int = -1
)

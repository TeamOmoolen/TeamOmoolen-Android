package com.omoolen.omooroid.onboarding.fragments.three

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ThreeOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
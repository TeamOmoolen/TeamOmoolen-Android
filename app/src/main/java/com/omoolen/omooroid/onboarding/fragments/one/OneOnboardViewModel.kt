package com.omoolen.omooroid.onboarding.fragments.one

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class OneOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
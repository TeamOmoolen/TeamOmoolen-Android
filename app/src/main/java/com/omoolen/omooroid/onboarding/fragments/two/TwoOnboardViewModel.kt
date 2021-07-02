package com.omoolen.omooroid.onboarding.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TwoOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
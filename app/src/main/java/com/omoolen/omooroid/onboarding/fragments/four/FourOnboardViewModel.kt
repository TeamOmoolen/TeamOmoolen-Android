package com.omoolen.omooroid.onboarding.fragments.four

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class FourOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
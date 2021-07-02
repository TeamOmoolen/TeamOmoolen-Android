package com.omoolen.omooroid.home.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TwoHomeViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
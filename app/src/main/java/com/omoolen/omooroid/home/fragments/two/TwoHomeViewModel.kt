package com.omoolen.omooroid.home.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class TwoHomeViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext


}
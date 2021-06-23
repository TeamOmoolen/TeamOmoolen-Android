package com.example.practices.home.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TwoViewModel(application: Application) : AndroidViewModel(application){
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext




}
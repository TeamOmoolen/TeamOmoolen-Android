package com.omoolen.omooroid.home.fragments.two

//import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.curating.CuratingInfo
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo

class TwoHomeViewModel(application: Application) : AndroidViewModel(application) {
    //@SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext

    val tabItem1 : String = "for you"
    val tabItem2 : String = "운동할 때"
    val tabItem3 : String = "신제품"
    val tabItem4 : String = "여름에 예쁜"

}
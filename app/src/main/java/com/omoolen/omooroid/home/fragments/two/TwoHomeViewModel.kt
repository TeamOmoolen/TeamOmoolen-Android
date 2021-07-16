package com.omoolen.omooroid.home.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.curating.CuratingInfo
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationByUser
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo
import com.omoolen.omooroid.home.homeApi.Data
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TwoHomeViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext

    var tabItem1 : String = "for you"
    var tabItem2 : String = "운동할 때"
    var tabItem3 : String = "신제품"
    var tabItem4 : String = "여름에 예쁜"

    lateinit var suggestData : Data
    val forYouList = ListLiveData<RecommendationBySituation>()
    val forNewList = ListLiveData<RecommendationBySituation>()
    val forSeasonList = ListLiveData<RecommendationBySituation>()
    val forSituationList = ListLiveData<RecommendationBySituation>()

    @SuppressLint("CheckResult")
    fun getSuggestData() {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getSuggestData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({suggest ->
                forYouList.clear()
                forNewList.clear()
                forSeasonList.clear()
                forSituationList.clear()

                suggestData = Data(suggest.data.season,suggest.data.situation,
                    suggest.data.suggestForNew,suggest.data.suggestForSeason,suggest.data.suggestForSituation,
                    suggest.data.suggestForYou)


                tabItem2 = suggestData.situation
                tabItem4 =suggestData.season

                forYouList.addAll(suggestData.suggestForYou as List<RecommendationBySituation>)
                forSituationList.addAll(suggestData.suggestForSituation as List<RecommendationBySituation>)
                forSeasonList.addAll(suggestData.suggestForSeason as List<RecommendationBySituation>)
                forNewList.addAll(suggestData.suggestForNew as List<RecommendationBySituation>)


                Log.d("**********FORSITU2", suggestData.suggestForSituation.toString())
                Log.d("**********FORSITU", forSituationList.size().toString())
                Log.d("**********FORNEW", forNewList.size().toString())
                Log.d("**********FORYOU", forYouList.size().toString())

                for(n in 0 until forSituationList.size())
                    Log.d("**********FORYOU", forSituationList[n].name)

                Log.d("RETROFIT_","$suggestData")
            },{e ->
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }
}
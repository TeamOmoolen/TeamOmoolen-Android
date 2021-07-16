package com.omoolen.omooroid.home

//import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.home.homeApi.Data
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val id = MutableLiveData<String>()
    lateinit var suggestData :Data
    fun getSuggestData() {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getSuggestData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({suggest ->

                suggestData = Data(suggest.data.season,suggest.data.situation,
                suggest.data.suggestForNew,suggest.data.suggestForSeason,suggest.data.suggestForSituation,
                suggest.data.suggestForYou)

                Log.d("RETROFIT_","$suggestData")
            },{e ->
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }
}


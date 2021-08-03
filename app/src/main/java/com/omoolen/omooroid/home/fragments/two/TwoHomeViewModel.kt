package com.omoolen.omooroid.home.fragments.two

//import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.two.api.Item
import com.omoolen.omooroid.home.homeApi.Data
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TwoHomeViewModel(application: Application) : AndroidViewModel(application) {
    //@SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext

    val tabItem1 : String = "for you"
    private val _tabItem2 = MutableLiveData<String>()
    val tabItem2: LiveData<String>
        get() = _tabItem2
    var tabItem3 : String = "신제품"
    private val _tabItem4 = MutableLiveData<String>()
    val tabItem4: LiveData<String>
        get() = _tabItem4

    lateinit var suggestData : Data
    val forYouList = ListLiveData<RecommendationBySituation>()
    val forNewList = ListLiveData<RecommendationBySituation>()
    val forSeasonList = ListLiveData<RecommendationBySituation>()
    val forSituationList = ListLiveData<RecommendationBySituation>()

    //@SuppressLint("CheckResult")
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

                if(_tabItem2.value == null) {
                    if (suggestData.situation.equals("일상")) {
                        _tabItem2.value = suggestData.situation + "에서"
                    } else {
                        _tabItem2.value = suggestData.situation + "할 때"
                    }
                }
                if(_tabItem4.value == null) {
                    if (suggestData.season.equals("summer")) {
                        _tabItem4.value = "여름에 예쁜"
                    } else {
                        _tabItem4.value = "겨울에 예쁜"
                    }
                }

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


    //각 프래그먼트 정렬용.

    val fy_totalPage = MutableLiveData<Int>() //for_you total page
    val si_totalPage = MutableLiveData<Int>() //situation total page
    val ni_totalPage = MutableLiveData<Int>() //new item total page
    val ss_totalPage = MutableLiveData<Int>() //season total page

    fun getForyou(page:Int,sort:String,order:String) {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getForyou(page = page,sort = sort,order = order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({each ->
               /* //기존.
               itemList.clear()
                Log.d("FORYOU",each.message)
                each.data.items.forEach{
                    itemList.add(
                        Item(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                        it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price)
                    )
                }

                totalPage.value = each.data.totalPage
                for(i in 0 until itemList.size())
                    Log.d("FORYOU",itemList[i].price.toString())*/

                //itemList에 안담고 그냥 바로 forYouList에 담아버림.
                if(page == 1 ) forYouList.clear()
                Log.d("FORYOU",each.message)
                each.data.items.forEach{
                    forYouList.add(
                        RecommendationBySituation(
                            it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                            it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price
                        )
                    )
                }
                fy_totalPage.value = each.data.totalPage
                for(i in 0 until forYouList.size())
                    Log.d("FORYOU",forYouList[i].price.toString())


            },{e ->
                Log.d("FORYOU","에러")
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }

    fun getSituation(page:Int,sort:String,order:String) {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getSituation(page = page,sort = sort,order = order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({each ->

                if(page == 1 ) forSituationList.clear()
                Log.d("SITUATION",each.message)
                each.data.items.forEach{
                    forSituationList.add(
                        RecommendationBySituation(
                            it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                            it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price
                        )
                    )
                }
                si_totalPage.value = each.data.totalPage
                for(i in 0 until forSituationList.size())
                    Log.d("SITUATION",forSituationList[i].price.toString())


            },{e ->
                Log.d("SITUATION","에러")
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }

    fun getSeason(page:Int,sort:String,order:String) {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getSeason(page = page,sort = sort,order = order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({each ->

                if(page == 1 ) forSeasonList.clear()
                Log.d("SEASON",each.message)
                each.data.items.forEach{
                    forSeasonList.add(
                        RecommendationBySituation(
                            it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                            it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price
                        )
                    )
                }
                ss_totalPage.value = each.data.totalPage
                for(i in 0 until forSeasonList.size())
                    Log.d("SEASON",forSeasonList[i].price.toString())


            },{e ->
                Log.d("SEASON","에러")
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }

    fun getNews(page:Int,sort:String,order:String) {

        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getNew(page = page,sort = sort,order = order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({each ->

                if(page == 1 ) forNewList.clear()
                Log.d("NEW",each.message)
                each.data.items.forEach{
                    forNewList.add(
                        RecommendationBySituation(
                            it.brand,it.changeCycleMaximum,it.changeCycleMinimum,it.diameter,
                            it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price
                        )
                    )
                }
                ni_totalPage.value = each.data.totalPage
                for(i in 0 until forNewList.size())
                    Log.d("NEW",forNewList[i].price.toString())


            },{e ->
                Log.d("NEW","에러")
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }
}
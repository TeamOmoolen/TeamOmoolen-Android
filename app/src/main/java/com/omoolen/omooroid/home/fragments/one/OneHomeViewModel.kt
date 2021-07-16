package com.omoolen.omooroid.home.fragments.one

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.fragments.one.curating.CuratingInfo
import com.omoolen.omooroid.home.fragments.one.event.EventInfo
import com.omoolen.omooroid.home.fragments.one.networkApi.*
import com.omoolen.omooroid.home.fragments.one.newItem.NewInfo
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo
import com.omoolen.omooroid.home.fragments.one.tip.TipInfo
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OneHomeViewModel(application: Application) : AndroidViewModel(application) {
    val deadlineEventList = ListLiveData<DeadlineEvent>()

    //guide
    var guide1 = ArrayList<Guide>() //각 GUIDE객체의 category,guides
    var guide2 = ArrayList<Guide>()
    var guide3 = ArrayList<Guide>()

    val guideLists = ListLiveData<GuideList1>() //guide1,guide2,guide3의 모임

    val lastestEventList = ListLiveData<LastestEvent>()

    val recommendationBySeasonList = ListLiveData<RecommendationBySeason>()
    val recommendationBySituationList = ListLiveData<RecommendationBySituation>()
    val recommendationByUserList = ListLiveData<RecommendationByUser>()
    val season = MutableLiveData<String>()
    val situation = MutableLiveData<String>()
    val userName = MutableLiveData<String>()

    val newlens1 = ArrayList<NewLensBrand1>()
    val newlens2 = ArrayList<NewLensBrand1>()
    val newlens3 = ArrayList<NewLensBrand1>()

    val newItemList = ListLiveData<NewInfo>()

    @SuppressLint("CheckResult")
    fun getHome() {

        Log.d("RETROFIT_HOME","시작")
            RetrofitClient.getApi.getHomeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({home ->
                Log.d("RETROFIT_HOME",home.data.username)
                deadlineEventList.clear()
                lastestEventList.clear()
                recommendationBySeasonList.clear()
                recommendationBySituationList.clear()
                recommendationByUserList.clear()

                home.data.deadlineEvent.forEach{
                    deadlineEventList.add(DeadlineEvent(it.id,it.image))
                }

//                guideList.add(GuideList1(home.data.guides.guideList1.category,home.data.guides.guideList1.guides))
//                guideList2.add(GuideList2(home.data.guides.guideList2.category,home.data.guides.guideList2.guides))
//                guideList3.add(GuideList3(home.data.guides.guideList3.category,home.data.guides.guideList3.guides))

                home.data.guides.guideList1.guides.forEach {
                    guide1.add(Guide(it.answer,it.id,it.question))
                }
                home.data.guides.guideList2.guides.forEach{
                    guide2.add(Guide(it.answer,it.id,it.question))
                }
                home.data.guides.guideList3.guides.forEach{
                    guide3.add(Guide(it.answer,it.id,it.question))
                }

                guideLists.add(GuideList1(home.data.guides.guideList1.category,guide1))
                guideLists.add(GuideList1(home.data.guides.guideList2.category,guide2))
                guideLists.add(GuideList1(home.data.guides.guideList3.category,guide3))


                home.data.lastestEvent.forEach{
                    lastestEventList.add(LastestEvent(it.id,it.image))
                }

                home.data.recommendationBySeason.forEach {
                    recommendationBySeasonList.add(RecommendationBySeason(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,
                    it.diameter,it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price))
                }

                home.data.recommendationBySituation.forEach{
                    recommendationBySituationList.add(RecommendationBySituation(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,
                        it.diameter,it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price))
                }

                home.data.recommendationByUser.forEach{
                    recommendationByUserList.add(RecommendationByUser(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,
                        it.diameter,it.id,it.imageList,it.name,it.otherColorList,it.pieces,it.price))
                }
                season.value =  home.data.season
                situation.value = home.data.situation
                userName.value = home.data.username

                home.data.newLens.newLensBrand1.forEach{
                    newlens1.add(NewLensBrand1(it.brand,it.id,it.imageList,it.name,it.price))
                }
                home.data.newLens.newLensBrand2.forEach{
                    newlens2.add(NewLensBrand1(it.brand,it.id,it.imageList,it.name,it.price))
                }
                home.data.newLens.newLensBrand3.forEach{
                    newlens2.add(NewLensBrand1(it.brand,it.id,it.imageList,it.name,it.price))
                }

                newItemList.add(NewInfo(newlens1))
                newItemList.add(NewInfo(newlens2))
                newItemList.add(NewInfo(newlens3))


                //Log찍기
                for(g in guide1)
                    Log.d("*********GUIDE","$g")
                for(n in 0 until newlens1.size)
                    Log.d("**********NEW",newlens1[n].name)


            },{e ->
                println(e.toString())
                Log.d("RETROFIT","에러")
            })
        Log.d("RETROFIT","끝")
    }



    private val _curatingList = MutableLiveData<List<CuratingInfo>>()
    val curatingList: LiveData<List<CuratingInfo>>
        get() = _curatingList

    private val _recommendList1 = MutableLiveData<List<RecommendInfo>>()
    val recommendList1: LiveData<List<RecommendInfo>>
        get() = _recommendList1

    private val _recommendList2 = MutableLiveData<List<RecommendInfo>>()
    val recommendList2: LiveData<List<RecommendInfo>>
        get() = _recommendList2

    private val _eventList = MutableLiveData<List<EventInfo>>()
    val eventList: LiveData<List<EventInfo>>
        get() = _eventList

    private val _adList = MutableLiveData<List<EventInfo>>()
    val adList: LiveData<List<EventInfo>>
        get() = _adList

    private val _tipList = MutableLiveData<List<TipInfo>>()
    val tipList: LiveData<List<TipInfo>>
        get() = _tipList

    private val _newList = MutableLiveData<List<NewInfo>>()
    val newList: LiveData<List<NewInfo>>
        get() = _newList


    fun setCuratingList() {
        _curatingList.value = mutableListOf(
            CuratingInfo(1,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                        "#597838", "#9249f6")
                ),
            CuratingInfo(2,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                        "#597838", "#9249f6")
            ),
            CuratingInfo(3,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                        "#597838", "#9249f6")
            ),
            CuratingInfo(4,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                        "#597838", "#9249f6")
            ),
            CuratingInfo(5,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈5", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            CuratingInfo(6,
                R.drawable.img_ellipse_1842, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈6", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            )
        )
    }

    fun setRecommend1List() {
        _recommendList1.value = mutableListOf(
            RecommendInfo(1,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈1", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(2,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈2", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(3,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(4,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(5,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈3", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(6,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈4", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            )
        )
    }

    fun setRecommend2List() {
        _recommendList2.value = mutableListOf(
            RecommendInfo(1,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈7", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(2,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈8", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(2,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈9", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(3,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈10", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(4,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈11", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            ),
            RecommendInfo(5,
                R.drawable.rectangle_3410, R.drawable.img_color_a,
                "오렌즈", "브라운 컬러렌즈12", "11.9mm / 1Day(10p)",
                18000, mutableListOf("#c4c4c4", "#ffca6c",
                    "#597838", "#9249f6")
            )
        )
    }

    fun setEventList() {
        _eventList.value = mutableListOf(
            EventInfo(1,
                R.drawable.e3
            ),
            EventInfo(2,
                R.drawable.e3
            ),
            EventInfo(3,
                R.drawable.e3
            )
        )
    }

    fun setAdList(){
        _adList.value = mutableListOf(
            EventInfo(1,
                R.drawable.newentry_web_mo_1
            ),
            EventInfo(2,
                R.drawable.newentry_web_mo_1
            ),
            EventInfo(3,
                R.drawable.newentry_web_mo_1
            )
        )
    }

    /*fun setNewList(){
        _newList.value = mutableListOf(
            NewInfo(1,
               R.drawable.img_group_7409,
               R.drawable.img_lensme_logo,
                "오렌즈",
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a ),
            NewInfo(2,
                R.drawable.img_group_7409,
                R.drawable.img_lensme_logo,
                "오렌즈",
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a ),
            NewInfo(3,
                R.drawable.img_group_7409,
                R.drawable.img_lensme_logo,
                "오렌즈",
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a,
                "브라운 컬러 익스 렌즈",
                18000,
                R.drawable.img_color_a ),
        )
    }*/
    fun setTipList(){
        _tipList.value = mutableListOf(
            TipInfo(1,
                R.drawable.ic_btn_translens_normal,
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명"
            ),
            TipInfo(2,
                R.drawable.ic_btn_translens_normal,
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명"
            ),
            TipInfo(3,
                R.drawable.ic_btn_translens_normal,
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명",
                "랜즈 눈치 안보고 패션을 꾸밀 수 있다?",
                "1분 렌즈 상식에 대한 간단 설명"
            )
        )
    }


}
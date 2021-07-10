package com.omoolen.omooroid.home.fragments.one

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omoolen.omooroid.R
import com.omoolen.omooroid.RetrofitBuilder_One
import com.omoolen.omooroid.home.fragments.one.curating.CuratingInfo
import com.omoolen.omooroid.home.fragments.one.event.EventInfo
import com.omoolen.omooroid.home.fragments.one.networkApi.*
import com.omoolen.omooroid.home.fragments.one.newItem.NewInfo
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendInfo
import com.omoolen.omooroid.home.fragments.one.tip.TipInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OneHomeViewModel(application: Application) : AndroidViewModel(application) {

    //서버 연결용
    private val _recommendListByUser = MutableLiveData<List<FindRecomendationByUser>>()
    val recommendListByUser: LiveData<List<FindRecomendationByUser>>
        get() = _recommendListByUser

    fun requestRecommendListByUser() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _recommendListByUser.postValue(RetrofitBuilder_One.oneService.getOne().data.findRecomendationByUser)
        } catch (e: HttpException) {

        }
    }

    private val _recommendListBySeason = MutableLiveData<List<RecommendationBySeason>>()
    val recommendListBySeason: LiveData<List<RecommendationBySeason>>
        get() = _recommendListBySeason

    fun requestRecomendationBySeasonr() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _recommendListBySeason.postValue(RetrofitBuilder_One.oneService.getOne().data.recommendationBySeason)
        } catch (e: HttpException) {

        }
    }

    private val _recommendListBySituation = MutableLiveData<List<RecommendationBySituation>>()
    val recommendListBySituation: LiveData<List<RecommendationBySituation>>
        get() = _recommendListBySituation

    fun requestRecomendationBySituation() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _recommendListBySituation.postValue(RetrofitBuilder_One.oneService.getOne().data.recommendationBySituation)
        } catch (e: HttpException) {

        }
    }

    private val _newLensList = MutableLiveData<NewLens>()
    val newLensList : LiveData<NewLens>
        get() = _newLensList

    fun requestNewLens() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _newLensList.postValue(RetrofitBuilder_One.oneService.getOne().data.newLens)
        } catch (e: HttpException) {

        }
    }


    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

    fun requestUserName() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _userName.postValue(RetrofitBuilder_One.oneService.getOne().data.username)
        } catch (e: HttpException) {

        }
    }

    private val _deadlineEventList = MutableLiveData<List<DeadlineEvent>>()
    val deadlineEventList: LiveData<List<DeadlineEvent>>
        get() = _deadlineEventList

    fun requestDeadlineEvent() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _deadlineEventList.postValue(RetrofitBuilder_One.oneService.getOne().data.deadlineEvent)
        } catch (e: HttpException) {

        }
    }

   private val _lastestEventList = MutableLiveData<List<LastestEvent>>()
    val lastestEventList: LiveData<List<LastestEvent>>
        get() = _lastestEventList

    fun requestLastestEvent() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _lastestEventList.postValue(RetrofitBuilder_One.oneService.getOne().data.lastestEvent)
        } catch (e: HttpException) {

        }
    }

    private val _guideList = MutableLiveData<List<Guide>>()
    val guideList: LiveData<List<Guide>>
        get() = _guideList

    fun requestGuideList() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _guideList.postValue(RetrofitBuilder_One.oneService.getOne().data.guides)
        } catch (e: HttpException) {

        }
    }






    //아래는 더미데이터

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

    fun setNewList(){
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
    }
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
package com.omoolen.omooroid.search

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.util.api.RetrofitClient
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.SharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchViewModel() : ViewModel() {
    var recentSearch = ListLiveData<RecentInfo>()

    fun initRecentSearch(context: Context){
        //TODO : sharedPreference에 있는 리스트 값 recentSearch에 초기화
        var localRecent = SharedPreferences.getStringArrayPref(context,"RECENT_KEY")
        if (localRecent != null) {
            for (i in 0 until localRecent.size) {
                recentSearch.add(RecentInfo(localRecent?.get(i)))
            }
        }
    }

    //최근 검색어 position 삭제
    fun deleteRecent(position: Int) {
        recentSearch.removeAt(position)
    }

    //최근 검색어 목록 전체 삭제
    fun deleteRecentAll() {
        recentSearch.removeAll()
    }

    //최근 검색어 추가
    fun addRecent(recent: String) {
        if(recentSearch.size() > 2){ //3개까지
            for(i in 0 until recentSearch.size()-2){
                recentSearch.removeAt(0)
            }
            //recentSearch.removeAt(0)
        }
        recentSearch.add(RecentInfo(recent))
    }

    //recentSearch 업데이트 상황 recentAdapter에 적용
    fun updateRecent(context:Context, recentSearch: MutableList<RecentInfo>, recentAdapter: RecentAdapter) {
        //sharedPreference
        SharedPreferences.setStringArrayPref(context,"RECENT_KEY",recentSearch)

        recentAdapter.recentList.clear()
        recentAdapter.recentList.addAll(recentSearch)
        recentAdapter.notifyDataSetChanged()
    }

    fun getUpdateRecent(recentList: MutableList<RecentInfo>): MutableList<RecentInfo> {
        return recentList
    }

    var mTotalPages : Int = 0

    @SuppressLint("CheckResult")
    fun getSearch(keyword: String) {
        val searchList = ListLiveData<Item>()
        Log.d("RETROFIT","시작")
        RetrofitClient.getApi.getData(keyword = keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({keyword ->
                searchList.clear()
                Log.d("RETROFIT_ENTER", keyword.data.totalPage.toString())
                //Log.d("RETROFIT_ENTER",keyword.data.items[0].brand)

                keyword.data.items.forEach{
                    searchList.add(Item(it.brand,it.changeCycleMaximum,it.changeCycleMinimum,
                        it.diameter,it.id,it.imageList,it.name,
                        it.otherColorList,it.pieces,it.price))
                }
                mTotalPages = keyword.data.totalPage

                //로그
                for(s in 0 until searchList.size()){
                    searchList[s].show()
                }
            },{e ->
                println(e.toString())
            })
        Log.d("RETROFIT","끝")
    }
}
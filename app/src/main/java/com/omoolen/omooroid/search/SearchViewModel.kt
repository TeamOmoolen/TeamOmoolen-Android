package com.omoolen.omooroid.search

import android.content.Context
import androidx.lifecycle.ViewModel
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.util.ListLiveData
import com.omoolen.omooroid.util.SharedPreferences


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
            recentSearch.removeAt(0)
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

}
package com.omoolen.omooroid.search.fragment.one

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.util.ListLiveData

class OneSearchViewModel() : ViewModel() {




    //TODO : 상세페이지로 이동
    fun intentToDetail(){

    }



    fun setPopularAdapter(_popularAdapter:PopularAdapter): PopularAdapter {
        //TODO : 랭킹 매겨서 popularInfo에 넘기기
        var popularAdapter = _popularAdapter
        popularAdapter.popularList.addAll(
            listOf<PopularInfo>(
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이2"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이3"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이4"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이2"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이3"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이4"),
                PopularInfo(rank = 1, name = "오렌즈 시크리스 그레이3")
            )
        )
        popularAdapter.setItemClickListener(object : PopularAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //position에 해당하는 상세 페이지로 intent
                //Toast.makeText(mContext,position.toString(),Toast.LENGTH_SHORT).show()
                //popularAdapter.popularList[0].name 를 GET
            }
        })
            return popularAdapter
    }
}
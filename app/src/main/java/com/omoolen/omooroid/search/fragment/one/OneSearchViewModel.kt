package com.omoolen.omooroid.search.fragment.one

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.util.ListLiveData

class OneSearchViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = getApplication<Application>().applicationContext
    private lateinit var recentAdapter: RecentAdapter
    private lateinit var popularAdapter: PopularAdapter

    val recentSearch = ListLiveData<String>()

    fun deleteRecent(position: Int) {
        recentAdapter.recentList.removeAt(position)
        recentAdapter.notifyDataSetChanged()
    }

    fun deleteRecentAll() {
        for (i in 0 until recentAdapter.recentList.size) {
            recentAdapter.recentList.removeAt(i)
        }
        recentAdapter.notifyDataSetChanged()
    }

    fun setRecentAdapter(): RecentAdapter {
        recentAdapter = RecentAdapter()
        recentAdapter.recentList.addAll(
            listOf<RecentInfo>(
                RecentInfo(name = "오렌즈 시크리스 그레이"),
                RecentInfo(name = "오렌즈 시크리스 그레이2"),
                RecentInfo(name = "오렌즈 시크리스 그레이3"),
            )
        )
        //TODO : 상세페이지로 INTET, X 클릭시 삭제 이벤트
        recentAdapter.setItemClickListener(object : RecentAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //position에 해당하는 상세 페이지로 intent
                //recentAdapter.
                //Toast.makeText(mContext,v.toString()+" "+position.toString(),Toast.LENGTH_SHORT).show()
                //Log.d("TESTRECENT",v.toString())

            }
        })
        return recentAdapter
    }

    fun setPopularAdapter(): PopularAdapter {
        //TODO : 랭킹 매겨서 popularInfo에 넘기기

        popularAdapter = PopularAdapter(mContext)
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
                Toast.makeText(mContext,position.toString(),Toast.LENGTH_SHORT).show()
            }
        })
            return popularAdapter

    }
}
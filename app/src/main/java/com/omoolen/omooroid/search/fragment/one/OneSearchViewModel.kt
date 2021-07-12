package com.omoolen.omooroid.search.fragment.one

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo

class OneSearchViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext
    private lateinit var recentAdapter: RecentAdapter
    private lateinit var popularAdapter: PopularAdapter


    fun setRecentAdapter(): RecentAdapter {
        recentAdapter = RecentAdapter()
        recentAdapter.recentList.addAll(
            listOf<RecentInfo>(
                RecentInfo(name = "오렌즈 시크리스 그레이"),
                RecentInfo(name = "오렌즈 시크리스 그레이2"),
                RecentInfo(name = "오렌즈 시크리스 그레이3"),
            )
        )
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
        return popularAdapter
    }
}
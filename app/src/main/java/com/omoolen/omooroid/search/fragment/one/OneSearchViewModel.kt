package com.omoolen.omooroid.search.fragment.one

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import com.omoolen.omooroid.util.ListLiveData

class OneSearchViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext
    lateinit var recentAdapter :RecentAdapter
    lateinit var popularAdapter : PopularAdapter

    val recentSearch =  ListLiveData<RecentInfo>()

    //최근 검색어 position 삭제
    fun deleteRecent(position: Int) {
        recentAdapter.recentList.removeAt(position)
        recentAdapter.notifyDataSetChanged()
    }

    //최근 검색어 목록 전체 삭제
    fun deleteRecentAll() {
        for (i in 0 until recentAdapter.recentList.size) { //전체 삭제
            Log.d("DELETE",i.toString())
            recentAdapter.recentList.removeAt(0)
        }
        recentAdapter.notifyDataSetChanged()
    }

    fun addRecent(recent:String){
        Log.d("ONESEARCHVIEWMODEL",recent)
        //recentAdapter.recentList.add(RecentInfo(name = recent))
        //recentAdapter.notifyDataSetChanged()
        recentSearch.add(RecentInfo(recent))
        Log.d("ADDRECENT",recentSearch.size().toString())
    }

    private var first:Int = 0
    fun updateRecent(recentList: MutableList<RecentInfo>){
        Log.d("FIRST",first.toString())
        first += 1
        if (first>0) {
//            var list = ArrayList<RecentInfo>()
//            for (recent in recentList) {
//                list.add(RecentInfo(name = recent))
//            }
            //recentAdapter.recentList.clear()
            recentAdapter.recentList.addAll(recentList)
            Log.d("ONESEARCHVIEWMODEL", "****" + recentAdapter.recentList.size.toString())
            recentAdapter.notifyDataSetChanged()
        }
    }

    //TODO : 상세페이지로 이동
    fun intentToDetail(){

    }

    fun setRecentAdapter(): RecentAdapter {
        recentAdapter = RecentAdapter()
        recentAdapter.recentList.addAll(
            listOf<RecentInfo>(
                RecentInfo(name = "오렌즈 시크리스 그레이"),
                RecentInfo(name = "오렌즈 시크리스 그레이2"),
                RecentInfo(name = "오렌즈 시크리스 그레이3")
            )
        )
        Log.d("ONESEARCHVIEWMODEL","추가")


        recentAdapter.setItemClickListener(object : RecentAdapter.OnItemClickListener {
            override fun deleteRecentOnClick(v: View, position: Int) {
                deleteRecent(position)
            }
            override fun searchOnClick(v: View, position: Int) {
                //TODO : 상세페이지로 이동
                intentToDetail()
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
                //popularAdapter.popularList[0].name 를 GET
            }
        })
            return popularAdapter
    }
}
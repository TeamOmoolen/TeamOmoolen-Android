package com.omoolen.omooroid.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.util.ListLiveData

class SearchDatabase {
    //싱글톤 객체 생성
    companion object{
        lateinit var searchData:SearchData
    }
    fun initSearch(){
        searchData = SearchData(mutableListOf(),mutableListOf(),mutableListOf(),-1)
    }
    fun setBrand(list:List<String>){
        searchData.brand.clear()
        for (i in 0 until list.size){
            searchData.brand.add(list[i])
        }
    }
    fun setCycle(list:List<Int>){
        searchData.changeCycleRange.clear()
        for (i in 0 until list.size){
            searchData.changeCycleRange.add(list[i])
        }
    }
    fun setDiameter(list:List<Int>){
        searchData.diameter = list[0]
    }
    fun setColor(list:List<String>){
        searchData.color.clear()
        for (i in 0 until list.size){
            searchData.color.add(list[i])
        }
    }

    fun show(){
        for (i in searchData.brand){
            Log.d("SEARCH_BRAND",i.toString())
        }
        for (i in searchData.color){
            Log.d("SEARCH_COLOR",i.toString())
        }
        for (i in searchData.changeCycleRange){
            Log.d("SEARCH_CYCLE",i.toString())
        }
        Log.d("SEARCH_DIAMETER",searchData.diameter.toString())
    }

}
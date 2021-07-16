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

//    val brandList = arrayOf("오렌즈", "렌즈미", "렌즈베리", "앤365", "렌즈타운", "다비치", "아이돌렌즈", "렌즈나인", "렌즈디바",
//        "아큐브", "바슈롬", "클라렌", "알콘", "뉴바이오", "프레쉬콘", "쿠퍼비전","기타") //기타는 쿠퍼비전
//    val colorList = arrayOf("clear", "black", "gray", "choco", "green", "brown", "purple", "blue", "gold",
//        "pink", "glitter")//기타면 otherColorList
//    val otherColorList = arrayOf("yellow", "espressogold", "hazel", "rich brown", "white", "red") //컬러 기타 눌렀을 때
//
//
//    fun convertBrand(indexArr: ListLiveData<Int>?):ArrayList<String>{
//        var array = ArrayList<String>()
//        if (indexArr != null) {
//            for(i in 0 until indexArr.size()){
//                array.add(brandList[indexArr[i]])
//            }
//        }
//        return array
//    }
//
//    fun convertColor(indexArr: ListLiveData<Int>?):ArrayList<String>{
//        var array = ArrayList<String>()
//        if (indexArr != null) {
//            for (i in 0 until indexArr.size()) {
//                Log.d("CONVERT_COLOR", i.toString())
//                if (indexArr[i] == 11) //기타
//                    array.addAll(otherColorList)
//                else
//                    array.add(colorList[indexArr[i]])
//            }
//        }
//        return array
//    }
//
//    fun convertDiameter(index:Int):Int{
//        return index
//    }
//    fun convertCycleindexArr(indexArr: ListLiveData<Int>?): ArrayList<Int> {
//        var array = ArrayList<Int>()
//        if (indexArr != null) {
//            for(i in 0 until indexArr.size()){
//                array.add(indexArr[i])
//            }
//        }
//        return array
//    }

}
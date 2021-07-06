package com.omoolen.omooroid.onboarding

import android.content.Context
import android.util.Log
import android.widget.Toast

class OnboardDatabase {
    //싱글톤 객체 생성
    companion object{
        lateinit var onboardData:OnboardData
    }
    fun initOnboard(){
        onboardData = OnboardData(-1,-1,arrayListOf(),arrayListOf(),-1,-1,-1,"",-1)
    }
    fun setOne(gender:Int,age:Int){
        onboardData._gender = gender
        onboardData._age = age
    }
    fun setTwo(what:ArrayList<Int>,color:ArrayList<Int>){
        onboardData._what = what
        onboardData._color = color
    }
    fun setThree(effect:Int,period:Int){
        onboardData._effect = effect
        onboardData._period = period
    }
    fun setFour(brand:Int,name:String,_when:Int){
        onboardData._brand = brand
        onboardData._name = name
        onboardData._when = _when
    }
    fun getOne():Int{
        return onboardData._gender
    }
}
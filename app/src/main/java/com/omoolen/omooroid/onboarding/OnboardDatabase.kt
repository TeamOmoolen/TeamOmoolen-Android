package com.omoolen.omooroid.onboarding

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.omoolen.omooroid.util.ListLiveData

class OnboardDatabase {
    //싱글톤 객체 생성
    companion object{
        lateinit var onboardData:OnboardData
    }
    fun initOnboard(){
        onboardData = OnboardData(-1,-1,null,null,-1,-1,-1,"",-1)
    }
    fun setOne(gender:Int,age:Int){
        onboardData._gender = gender
        onboardData._age = age
    }
    fun setTwo(what: ListLiveData<Int>, color:ListLiveData<Int>){
        onboardData._what = what
        onboardData._color = color
    }
    fun setThree(effect:Int, period:Int){
        onboardData._effect = effect
        onboardData._period = period
    }
    fun setFour(brand:Int,name:String,_when:Int){
        onboardData._brand = brand
        onboardData._name = name
        onboardData._when = _when
    }
    fun show(){
        Log.d("ONBOARD",
            onboardData._gender.toString()+","+
                onboardData._age.toString()+","+
                onboardData._what.toString()+","+
                onboardData._color.toString()+","+
                onboardData._effect.toString()+","+
                onboardData._period.toString()+","+
                onboardData._brand.toString()+","+
                onboardData._name.toString()+","+
                onboardData._when.toString()+","
        )
    }
}
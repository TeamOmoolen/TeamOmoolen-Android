package com.omoolen.omooroid.onboarding

import android.util.Log
import com.omoolen.omooroid.util.ListLiveData

class OnboardDatabase {
    //싱글톤 객체 생성
    companion object{
        lateinit var onboardData:OnboardData
    }
    fun getOnboardData():OnboardData{
        return onboardData
    }
    fun initOnboard(){
        onboardData = OnboardData(-1,-1,null,null,-1,null,-1,"",-1)
    }
    fun setOne(gender:Int,age:Int){
        onboardData._gender = gender
        onboardData._age = age
    }
    fun setTwo(what: ListLiveData<Int>, color:ListLiveData<Int>){
        onboardData._what = what
        onboardData._color = color
    }
    fun setThree(effect:Int, period:ListLiveData<Int>){
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

    var genderList = arrayOf("w","m")
    var ageList = arrayOf(10,20,30,40)
    val categoryList = arrayOf("컬러","투명","코스프레")
    val colorList = arrayOf("clear", "black", "gray", "choco", "green", "brown", "purple", "blue", "gold",
        "pink", "glitter")//기타면 otherColorList
    val otherColorList = arrayOf("yellow", "espressogold", "hazel", "rich brown", "white", "red") //컬러 기타 눌렀을 때
    val functionList = arrayOf("근시","난시","다초점","") //없음은 ""
    val changeList = arrayOf(0,1,2,3,4,5,6,7) //없음 있으면 무조건  0~7
    val brandList = arrayOf("오렌즈", "렌즈미", "렌즈베리", "앤365", "렌즈타운", "다비치", "아이돌렌즈", "렌즈나인", "렌즈디바",
        "아큐브", "바슈롬", "클라렌", "알콘", "뉴바이오", "프레쉬콘", "쿠퍼비전","기타") //기타는 쿠퍼비전
    val whenList = arrayOf("운동","일상생활","특별한 날","여행")

    fun convertGender(index:Int):String{
        return genderList[index]
    }
    fun convertAge(index:Int):Int{
        return ageList[index]
    }
    fun convertCategory(indexArr: ListLiveData<Int>?):ArrayList<String>{
        var array = ArrayList<String>()
        if (indexArr != null) {
            for(i in 0 until indexArr.size()){
                array.add(categoryList[indexArr[i]])
            }
        }
        return array
    }
    fun convertColor(indexArr: ListLiveData<Int>?):ArrayList<String>{
        var array = ArrayList<String>()
        if (indexArr != null) {
            for (i in 0 until indexArr.size()) {
                Log.d("CONVERT_COLOR", i.toString())
                if (indexArr[i] == 11) //기타
                    array.addAll(otherColorList)
                else
                    array.add(colorList[indexArr[i]])
            }
        }
        return array
    }
    fun convertFunction(index:Int):String{
        return functionList[index]
    }
    fun convertChange(indexArr: ListLiveData<Int>?):ArrayList<Int> {
        var array = ArrayList<Int>()
        if (indexArr != null) {
            for (index in 0 until indexArr.size()) {
                if (indexArr[index] == 8) {//없음
                    array.clear()
                    array.addAll(changeList)
                } else {
                    array.add(indexArr[index])
                }
            }
        }
        return array
    }
    fun convertBrand(index: Int): String {
        return brandList[index]
    }
    fun convertWhen(index:Int):String{
        return whenList[index]
    }
}
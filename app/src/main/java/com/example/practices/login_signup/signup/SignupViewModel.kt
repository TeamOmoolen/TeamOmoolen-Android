package com.example.practices.login_signup.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SignupViewModel(application: Application) : AndroidViewModel(application){
//    private val repository = MemberRepository(MyApplication.ApplicationContext() as Application) //activity의 생명주기가 아닌 application의 생명주기를 따르도록 함
//    private val members = repository.getAll()

    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess: LiveData<Boolean>
        get() = _signupSuccess

    fun checkInputText():Boolean{
        Log.d("SignupViewModelTest",name.value.toString()+"      "+id.value.toString()+"      "+password.value.toString())
        return name.value.isNullOrEmpty() || id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }

    //회원 정보 확인 위한 test function
//    fun getAll(): LiveData<List<Member>>{
//        return this.members
//    }
//
//    fun insert(member: Member){
//        repository.insert(member)
//    }
//
//    fun delete(member: Member){
//        repository.delete(member)
//    }
//
//    fun isDuplicate(userId:String) :Boolean{
//        Log.d("TESTDUP",repository.findById(userId).toString())
//        return repository.findById(userId) != null //duplicate -> true
//    }
//
//    fun doSignup(){
//        val requestSignupData = RequestSignupData(
//            email = id.value.toString(),
//            password = password.value.toString(),
//            birth = "2000-01-01",
//            sex = "0",
//            nickname = "test",
//            phone = "010-0000-0000"
//        )
//
//        val call: Call<ResponseSignupData> = SignupClient.signupService
//            .postSignup(requestSignupData)
//
//        call.enqueue(object : Callback<ResponseSignupData> {
//            override fun onResponse(
//                call: Call<ResponseSignupData>,
//                response: Response<ResponseSignupData>
//            ){
//                _signupSuccess.value = response.isSuccessful
//            }
//
//            override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
//                Log.d("NetworkTest","error:$t")
//            }
//        })
//    }
}
package com.omoolen.omooroid.onboarding.fragments.one

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeInfo
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderInfo
import com.omoolen.omooroid.util.ListLiveData

class OneOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext
    private lateinit var genderAdapter: GenderAdapter
    private lateinit var ageAdapter: AgeAdapter

    //Observe위해 livedata선언
    private val _gender = MutableLiveData<Int>()
    val gender: LiveData<Int>
        get() = _gender
    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int>
        get() = _age


    fun setGenderAdapter() :GenderAdapter{
        _gender.value = -1
        genderAdapter = GenderAdapter()
        genderAdapter.genderList.addAll(
            listOf<GenderInfo>(
                GenderInfo(resourceId = R.drawable.ic_colorlens,name = "여성"),
                GenderInfo(resourceId = R.drawable.onboard_female_selector,name = "남성")
            )
        )
        genderAdapter.setItemClickListener(object : GenderAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }
        })
        return genderAdapter
    }

    fun setAgeAdapter() :AgeAdapter{
        _age.value = -1
        ageAdapter = AgeAdapter()
        ageAdapter.ageList.addAll(
            listOf<AgeInfo>(
                AgeInfo(age = "10대"),
                AgeInfo(age = "20대"),
                AgeInfo(age = "30대"),
                AgeInfo(age = "40대 이상")
            )
        )
        ageAdapter.setItemClickListener(object : AgeAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }
        })
        return ageAdapter
    }

    fun genderSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _gender.value = position
            for (i in 0..rv.adapter!!.itemCount) {
                var otherView = rv.layoutManager?.findViewByPosition(i)
                if (otherView != view) {
                    otherView?.isSelected = false
                } else {
                }
            }
        }
    }
    fun ageSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _age.value = position
            for (i in 0..rv.adapter!!.itemCount) {
                var otherView = rv.layoutManager?.findViewByPosition(i)
                if (otherView != view) {
                    otherView?.isSelected = false
                } else {
                }
            }
        }
    }

}
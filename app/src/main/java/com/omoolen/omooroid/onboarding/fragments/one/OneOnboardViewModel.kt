package com.omoolen.omooroid.onboarding.fragments.one

import android.annotation.SuppressLint
import android.app.Application
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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

    //var genderArr = ListLiveData <Boolean>()
    //var ageArr = ListLiveData <Boolean>()

    fun setGenderAdapter() :GenderAdapter{
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

    fun singleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            //TODO : array 값 바꾸기
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            //arr값 변경
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
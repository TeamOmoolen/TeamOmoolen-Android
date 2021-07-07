package com.omoolen.omooroid.onboarding.fragments.three

import android.annotation.SuppressLint
import android.app.Application
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.onboarding.fragments.three.recycle.effect.EffectAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.effect.EffectInfo
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodAdapter
import com.omoolen.omooroid.onboarding.fragments.three.recycle.period.PeriodInfo

class ThreeOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext
    private lateinit var effectAdapter: EffectAdapter
    private lateinit var periodAdapter: PeriodAdapter

    //Observe위해 livedata선언
    private val _effect = MutableLiveData<Int>()
    val effect: LiveData<Int>
        get() = _effect
    private val _period = MutableLiveData<Int>()
    val period: LiveData<Int>
        get() = _period

    fun setEffectAdapter() :EffectAdapter{
        _effect.value = -1
        effectAdapter = EffectAdapter()
        effectAdapter.effectList.addAll(
            listOf<EffectInfo>(
                EffectInfo(effect = "근시"),
                EffectInfo(effect = "난시"),
                EffectInfo(effect = "다초점"),
                EffectInfo(effect = "없음")
            )
        )
        effectAdapter.setItemClickListener(object: EffectAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
            }
        })
        return effectAdapter
    }

    fun setPeriodAdapter() : PeriodAdapter{
        _period.value = -1
        periodAdapter = PeriodAdapter()
        periodAdapter.periodList.addAll(
            listOf<PeriodInfo>(
                PeriodInfo(period = "원데이"),
                PeriodInfo(period = "1주"),
                PeriodInfo(period = "2주"),
                PeriodInfo(period = "1개월"),
                PeriodInfo(period = "2~3개월"),
                PeriodInfo(period = "3~6개월"),
                PeriodInfo(period = "6개월 이상"),
                PeriodInfo(period = "없음")
            )
        )
        periodAdapter.setItemClickListener(object: PeriodAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
            }
        })
        return periodAdapter
    }

    fun effectSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _effect.value = position
            for (i in 0..rv.adapter!!.itemCount) {
                var otherView = rv.layoutManager?.findViewByPosition(i)
                if (otherView != view) {
                    otherView?.isSelected = false
                } else {
                }
            }
        }
    }

    fun periodSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _period.value = position
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
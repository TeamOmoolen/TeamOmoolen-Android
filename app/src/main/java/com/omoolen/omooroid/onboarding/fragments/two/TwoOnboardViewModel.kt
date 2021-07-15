package com.omoolen.omooroid.onboarding.fragments.two

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omoolen.omooroid.R
import com.omoolen.omooroid.onboarding.fragments.one.recycle.age.AgeAdapter
import com.omoolen.omooroid.onboarding.fragments.one.recycle.gender.GenderAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.color.ColorInfo
import com.omoolen.omooroid.onboarding.fragments.two.recycle.what.WhatAdapter
import com.omoolen.omooroid.onboarding.fragments.two.recycle.what.WhatInfo
import com.omoolen.omooroid.util.ListLiveData

class TwoOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext

    private lateinit var whatAdapter: WhatAdapter
    private lateinit var colorAdapter: ColorAdapter

    val whatChoice = ListLiveData<Int>()
    val colorChoice = ListLiveData<Int>()

    fun setWhatAdapter() : WhatAdapter{
        whatAdapter = WhatAdapter()
        whatAdapter.whatList.addAll(
            listOf<WhatInfo>(
                WhatInfo(resourceId = R.drawable.img_colorlens,name = "컬러렌즈"),
                WhatInfo(resourceId = R.drawable.ic_colorlens,name = "투명렌즈"),
                WhatInfo(resourceId = R.drawable.group_8060,name = "코스프레/공막 렌즈"),
            )
        )
        var whatArr = arrayOf(false,false,false)
        whatAdapter.setItemClickListener(object: WhatAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //다중 선택
                whatArr[position] = !whatArr[position]
                if(whatArr[position]) whatChoice.add(position)
                else whatChoice.remove(position)
                v.isSelected = whatArr[position]
            }
        })
        return whatAdapter
    }

    fun setColorAdapter() : ColorAdapter{
        colorAdapter = ColorAdapter()
        colorAdapter.colorList.addAll(
            listOf<ColorInfo>(
                ColorInfo(backId = R.drawable.ic_btn_noncolor_back, resourceId = R.drawable.ic_btn_noncolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_blackcolor_back, resourceId = R.drawable.ic_btn_blackcolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_greycolor_back, resourceId = R.drawable.ic_btn_greycolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_chococolor_back, resourceId = R.drawable.ic_btn_chococolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_greencolor_back, resourceId = R.drawable.ic_btn_greencolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_browncolor_back, resourceId = R.drawable.ic_btn_browncolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_purplecolor_back, resourceId = R.drawable.ic_btn_purplecolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_bluecolor_back, resourceId = R.drawable.ic_btn_bluecolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_goldcolor_back, resourceId = R.drawable.ic_btn_goldcolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_pinkcolor_back, resourceId = R.drawable.ic_btn_pinkcolor_selector),
                ColorInfo(backId = R.drawable.btn_glittercolor_normal, resourceId = R.drawable.ic_btn_glittercolor_selector),
                ColorInfo(backId = R.drawable.ic_btn_etccolor_normal, resourceId = R.drawable.ic_btn_etccolor_selector)
            )
        )
        var colorArr = arrayOf(false,false,false,false,false,false,false,false,false,false,false,false)
        colorAdapter.setItemClickListener(object: ColorAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // TODO : 클릭 시 이벤트 작성
                //Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
                //다중 선택 시 -> 어떻게 막을지 생각하기
                //단일 선택 시 -> position 저장하기
                colorArr[position] = !colorArr[position]
                if(colorArr[position]) colorChoice.add(position)
                else colorChoice.remove(position)
                v.isSelected = colorArr[position]

            }
        })
        return colorAdapter
    }
}
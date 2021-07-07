package com.omoolen.omooroid.onboarding.fragments.four

import android.annotation.SuppressLint
import android.app.Application
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenAdapter
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenInfo
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo

class FourOnboardViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val mContext = getApplication<Application>().applicationContext
    private lateinit var brandAdapter: BrandAdapter
    private lateinit var whenAdapter: WhenAdapter

    val lensName = MutableLiveData<String>()

    //Observe위해 livedata선언
    private val _brand = MutableLiveData<Int>()
    val brand: LiveData<Int>
        get() = _brand
    private val _when = MutableLiveData<Int>()
    val whens: LiveData<Int>
        get() = _when

    fun setBrandAdapter() :BrandAdapter{
        brandAdapter = BrandAdapter()
        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(resourceId = R.drawable.img_olens_logo, name = "오렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensme_logo, name = "렌즈미"),
                BrandInfo(resourceId = R.drawable.img_lensvery_logo, name = "렌즈베리"),
                BrandInfo(resourceId = R.drawable.img_ann_logo_1, name = "앤365"),
                BrandInfo(resourceId = R.drawable.img_lenstown_logo_1, name = "렌즈타운"),
                BrandInfo(resourceId = R.drawable.img_davi_logo, name = "다비치"),
                BrandInfo(resourceId = R.drawable.img_idol_logo, name = "아이돌렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensnine_logo_1, name = "렌즈나인"),
                BrandInfo(resourceId = R.drawable.img_lensdiva_logo, name = "렌즈디바"),
                BrandInfo(resourceId = R.drawable.img_acuvue_logo, name = "아큐브"),
                BrandInfo(resourceId = R.drawable.img_ba_logo, name = "바슈롬"),
                BrandInfo(resourceId = R.drawable.img_cl_logo, name = "클라렌"),
                BrandInfo(resourceId = R.drawable.img_alcon_logo_1, name = "알콘"),
                BrandInfo(resourceId = R.drawable.img_new_logo_1, name = "뉴바이"),
                BrandInfo(resourceId = R.drawable.image_41, name = "프레쉬콘"),
                BrandInfo(resourceId = R.drawable.image_42, name = "쿠퍼비전"),
                BrandInfo(resourceId = R.drawable.etc, name = "그외")
            )
        )

        brandAdapter.setItemClickListener(object: BrandAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
            }
        })
        return brandAdapter
    }

    fun setWhenAdapter() :WhenAdapter{
        whenAdapter = WhenAdapter()
        whenAdapter.whenList.addAll(
            listOf<WhenInfo>(
                WhenInfo(name = "운동"),
                WhenInfo(name = "일상생활"),
                WhenInfo(name = "특별한 날"),
                WhenInfo(name = "여행"),
                WhenInfo(name = "기타")
            )
        )

        whenAdapter.setItemClickListener(object: WhenAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
            }
        })
        return whenAdapter
    }

    fun brandSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _brand.value = position
            for (i in 0..rv.adapter!!.itemCount) {
                var otherView = rv.layoutManager?.findViewByPosition(i)
                if (otherView != view) {
                    otherView?.isSelected = false
                } else {
                }
            }
        }
    }

    fun whenSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _when.value = position
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
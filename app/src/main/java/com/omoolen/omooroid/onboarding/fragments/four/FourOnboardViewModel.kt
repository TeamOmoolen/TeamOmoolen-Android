package com.omoolen.omooroid.onboarding.fragments.four

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.onboarding.OnboardDatabase
import com.omoolen.omooroid.onboarding.api.*
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenAdapter
import com.omoolen.omooroid.onboarding.fragments.four.`when`.WhenInfo
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandAdapter
import com.omoolen.omooroid.onboarding.fragments.four.brand.BrandInfo
import com.omoolen.omooroid.util.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private val _brandName = MutableLiveData<String>()
    val brandName: LiveData<String>
        get() = _brandName
    private val _when = MutableLiveData<Int>()
    val whens: LiveData<Int>
        get() = _when

    fun setBrandAdapter() :BrandAdapter{
        brandAdapter = BrandAdapter()
        brandAdapter.brandList.addAll(
            listOf<BrandInfo>(
                BrandInfo(resourceId = R.drawable.img_olens_logo_onboarding_normal, name = "오렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensme_logo_onboarding_normal, name = "렌즈미"),
                BrandInfo(resourceId = R.drawable.img_lensvery_logo_onboarding_normal, name = "렌즈베리"),
                BrandInfo(resourceId = R.drawable.img_ann_logo_onboarding_normal, name = "앤365"),
                BrandInfo(resourceId = R.drawable.img_lenstown_logo_onboarding_normal, name = "렌즈타운"),
                BrandInfo(resourceId = R.drawable.img_davi_logo_onboarding_normal, name = "다비치"),
                BrandInfo(resourceId = R.drawable.img_idol_logo_onboarding_normal, name = "아이돌렌즈"),
                BrandInfo(resourceId = R.drawable.img_lensnine_logo_onboarding_normal, name = "렌즈나인"),
                BrandInfo(resourceId = R.drawable.img_lensdiva_logo_onboarding_normal, name = "렌즈디바"),
                BrandInfo(resourceId = R.drawable.img_acuvue_logo_onboarding_normal, name = "아큐브"),
                BrandInfo(resourceId = R.drawable.img_ba_logo_onboarding_normal, name = "바슈롬"),
                BrandInfo(resourceId = R.drawable.img_cl_logo_onboarding_normal, name = "클라렌"),
                BrandInfo(resourceId = R.drawable.img_ilcon_logo_onboarding_normal, name = "알콘"),
                BrandInfo(resourceId = R.drawable.img_newbio_logo_onboarding_normal, name = "뉴바이오"),
                BrandInfo(resourceId = R.drawable.img_freshkon_logo_onboarding_normal, name = "프레쉬콘"),
                BrandInfo(resourceId = R.drawable.img_coupervision_logo_onboarding_normal, name = "쿠퍼비전"),
                BrandInfo(resourceId = R.drawable.img_etc_logo_onboarding_normal, name = "그외")
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
                WhenInfo(name = "여행")
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
            _brandName.value = brandAdapter.brandList[position].name
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

    fun postOnboard(){
        val requestOnboardData = setRequestOnboardData() //전송할 데이터

        Log.d("SERVER_ONBOARD4","포스트 시작")
        val call: Call<ResponseOnboardData> = RetrofitClient.getApi.postOnboard(requestOnboardData)
        call.enqueue(object : Callback<ResponseOnboardData> {
            override fun onResponse(
                call: Call<ResponseOnboardData>,
                response: Response<ResponseOnboardData>
            ){
                Log.d("SERVER_ONBOARD44",response.isSuccessful.toString())
                Log.d("SERVER_ONBOARD44",response.body()?.status.toString())
                Log.d("SERVER_ONBOARD44",response.body()?.message.toString())
            }
            override fun onFailure(call: Call<ResponseOnboardData>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d("SERVER_ONBOARD4","포스트 실패")
            }
        })
    }

    private val oData = OnboardDatabase()
    private fun setRequestOnboardData(): RequestOnboardData {
        val suitedLens = SuitedLens(
            oData.convertBrand(oData.getOnboardData()._brand),
            oData.getOnboardData()._name
        )
        val wantedLens = WantedLens(
            oData.convertCategory(oData.getOnboardData()._what),
            oData.convertChange(oData.getOnboardData()._period),
            oData.convertColor(oData.getOnboardData()._color),
            oData.convertFunction(oData.getOnboardData()._effect)
        )

        return RequestOnboardData(
            oData.convertAge(oData.getOnboardData()._age),
            oData.convertGender(oData.getOnboardData()._gender),
            suitedLens,
            wantedLens,
            oData.convertWhen(oData.getOnboardData()._when)
        )
    }

}
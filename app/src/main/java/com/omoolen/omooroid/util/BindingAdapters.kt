package com.omoolen.omooroid.util


import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.omoolen.omooroid.R
import java.text.NumberFormat
import java.util.*

object BindingAdapters {
    @BindingAdapter("setSrc")
    @JvmStatic
    fun setSrc(imageView: ImageView, imgId: Int) {
        imageView.setImageResource(imgId)
    }

    @BindingAdapter("setPrice")
    @JvmStatic
    fun setPrice(textView : TextView, num: String) {
        val price = "$num 원"
        textView.text = price
    }

    @BindingAdapter("setDiameter")
    @JvmStatic
    fun setDiameter(textView : TextView, num: Double) {
        val price = "${num}mm"
        textView.text = price
    }

    @BindingAdapter("setPrice2")
    @JvmStatic
    fun setPrice2(textView : TextView, num: Int) {
        val price = NumberFormat.getNumberInstance(Locale.US)
            .format(num) + "원"
        textView.text = price
    }

    @BindingAdapter("setColor")
    @JvmStatic
    fun setColor(imageView: ImageView, code: String) {
        val color = (Color.parseColor(code))
        imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    @BindingAdapter("setUserMessage")
    @JvmStatic
    fun setUserMessage(textView: TextView, userName : String){
        val txt = userName + "님! 이 렌즈는 어때요?"
        textView.text = txt
    }


    @BindingAdapter("setColor2")
    @JvmStatic
    fun setColor2(imageView: ImageView, code: String) {
        Log.d("********COLOR_STRING", code)

        var color : String = when(code.trim()){
            "black" -> "#4F4F4F"
            "choco" -> "#524836"
            "gray", "grey" -> "#6A6F88"
            "green" -> "#65BD79"
            "brown" -> "#958057"
            "purple" -> "#C76BE7"
            "blue" -> "#7595E8"
            "gold" -> "#EAB86E"
            "pink" -> "#F372A1"
            "etc" -> "#FBFBFB"
            "glitter" -> "#EAB86E"
            else -> "#E8EFFF"
        }

        if(code == "etc"){
            imageView.setImageResource(R.drawable.lens_home_etccolor)
        } else if(code == "glitter"){
            imageView.setImageResource(R.drawable.lens_home_glitter)
        } else {
            imageView.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
        }
    // 기타일 경우 _ 이미지 바꿔치기
    // imageView.setImageResource(R.drawable.btn_glittercolor_normal)
    }


    @BindingAdapter("setMinRange", "setMaxRange", "setDiameter", "setPiece")
    @JvmStatic
    fun setPiece(textView: TextView, min : Int, max : Int, dia : Double, piece : Int) {
        val dm = "$dia" + "mm"
        var rg : String

        if(min < 7 || max < 7){
            if(min == max) rg = "$min" + "Day"
            else rg = "$min" + "~" + "$max"  + "Day"
        } else if(min < 30 || max < 30 ){
            val week  = min / 7
            rg = "$week" + "Week"

        } else {
            if(min == max) {
                val mth  = min / 30
                rg = "$mth" + "Week"
            } else {
                val mth1  = min / 30
                val mth2 = max / 30
                rg = "$mth1" + "~" + "$mth2"  + "Month"
            }
        }

        val rslt = "$dm / $rg(${piece}p)"
        textView.text = rslt
    }

    @BindingAdapter("setMinRange2", "setMaxRange2")
    @JvmStatic
    fun setMonth(textView: TextView, min : Int, max : Int) {
        var rg1 : String

        if(min < 7 || max < 7){
            if(min == max) rg1 = "$min" + "Day"
            else rg1 = "$min" + "~" + "$max"  + "Day"
        } else if(min < 30 || max < 30 ){
            val week  = min / 7
            rg1 = "$week" + "Week"

        } else {
            if(min == max) {
                val mth  = min / 30
                rg1 = "$mth" + "Week"
            } else {
                val mth1  = min / 30
                val mth2 = max / 30
                rg1 = "$mth1" + "~" + "$mth2"  + "Month"
            }
        }

        textView.text = "$rg1"
    }


    @BindingAdapter("setSrcFromUrl")
    @JvmStatic
    fun setSrcFromUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    @BindingAdapter("setItemCnt")
    @JvmStatic
    fun setItemCnt(textView: TextView, cnt : Int) {
        textView.text = "총 ${cnt}개의 상품"
    }

}
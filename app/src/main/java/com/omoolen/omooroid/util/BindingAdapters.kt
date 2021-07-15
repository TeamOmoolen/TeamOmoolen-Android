package com.omoolen.omooroid.util


import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
    fun setPrice(textView : TextView, num: Int) {
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
        var color : Int
        color = when(code){
            "black" -> R.color.lens_color_black
            "choco" -> R.color.lens_color_choco
            "gray", "grey" -> R.color.lens_color_grey
            "green" -> R.color.lens_color_green
            "brown" -> R.color.lens_color_brown
            "purple" -> R.color.lens_color_purple
            "blue" -> R.color.lens_color_blue
            "gold" -> R.color.lens_color_gold
            "pink" -> R.color.lens_color_pink
            "etc" -> R.color.lens_color_etc
            else -> R.color.lens_color_etc
        }
        imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)

    // 기타일 경우 _ 이미지 바꿔치기
    // imageView.setImageResource(R.drawable.btn_glittercolor_normal)
    }


    @BindingAdapter("setMinRange", "setMaxRange", "setDiaMeter")
    @JvmStatic
    fun setInfo(textView: TextView, min : Int, max : Int, dia : Double) {
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

        val rslt = "$dm / $rg"
        textView.text = rslt
    }
}
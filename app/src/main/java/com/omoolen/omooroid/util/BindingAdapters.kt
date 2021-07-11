package com.omoolen.omooroid.util


import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
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

    @BindingAdapter("setLenSize", "setLensType")
    @JvmStatic
    fun setLensInfo(textView: TextView, size: String, type: String) {
        val txt = "$size / $type"
        textView.text = txt
    }

    @BindingAdapter("setUserMessage")
    @JvmStatic
    fun setUserMessage(textView: TextView, userName : String){
        val txt = userName + "님! 이 렌즈는 어때요?"
        textView.text = txt
    }
}
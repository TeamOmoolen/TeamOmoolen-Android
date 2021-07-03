package com.omoolen.omooroid.onboarding.fragments.one


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.omoolen.omooroid.R


class AgeView : LinearLayout {
    var tv_age: TextView? = null

    // Generate > Constructor
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    // singer_item.xml을 inflation
    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.item_onboard_age, this, true)
        tv_age = findViewById<View>(R.id.tv_age) as TextView
    }

    fun setName(name: String?) {
        tv_age!!.text = name
    }
}



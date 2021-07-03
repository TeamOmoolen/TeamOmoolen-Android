package com.omoolen.omooroid.onboarding.fragments.one

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


 class AgeAdapter : BaseAdapter() {
    var items: ArrayList<AgeInfo> = ArrayList<AgeInfo>()

    // Generate > implement methods
    override fun getCount(): Int {
        return items.size
    }

    fun addItem(item: AgeInfo) {
        items.add(item)
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        // 뷰 객체 재사용
        var view: AgeView? = null
        if (convertView == null) {
            if (parent != null) {
                view = AgeView(parent.context)
            }
        } else {
            view = convertView as AgeView
        }
        val item: AgeInfo = items[position]
        view?.setName(item.getName())
        return view
    }
}
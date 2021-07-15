package com.omoolen.omooroid.search.fragment.two

import android.view.MotionEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.util.ListLiveData

class TwoSearchViewModel() : ViewModel() {
    val _diameterChoice = MutableLiveData<Int>()

    fun diameterSingleChoice(rv: RecyclerView, e: MotionEvent){
        var child = rv.findChildViewUnder(e.x, e.y)
        if (child != null) {
            var position = rv.getChildAdapterPosition(child)
            var view = rv.layoutManager?.findViewByPosition(position)
            view?.isSelected = true
            _diameterChoice.value = position
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
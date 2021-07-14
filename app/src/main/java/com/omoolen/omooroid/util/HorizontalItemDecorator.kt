package com.omoolen.omooroid.util

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HorizontalItemDecorator(private val divHeight : Int, private val count : Int, private val context: Context) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        //dp _ px 변환
        //val px : Int = dpToPx(context, divHeight)
        val px : Int = divHeight

        var lp: GridLayoutManager.LayoutParams? =
            view.layoutParams as GridLayoutManager.LayoutParams


        var spanIndex : Int? = null

        if(lp != null) {
            spanIndex = lp.spanIndex
        }

        if(count == 2) {
            if (spanIndex == 0) {
                //왼쪽 아이템
                outRect.right = -(px + 1.5).toInt()
            }
            if (spanIndex == 1) {
                //오른쪽 아이템
                outRect.left = -(px + 1.5).toInt()

            }
        } else if (count == 3) {
            if (spanIndex == 0) {
                //왼쪽 아이템
                outRect.right = -px
            }
            if (spanIndex == 2) {
                outRect.left = -(px)
            }
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}
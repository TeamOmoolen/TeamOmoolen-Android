package com.omoolen.omooroid.search.fragment

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.omoolen.omooroid.search.fragment.one.OneSearchFragment
import com.omoolen.omooroid.search.fragment.two.TwoSearchFragment

class SearchFragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm) {
    //position 에 따라 원하는 Fragment로 이동시키기
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OneSearchFragment().newInstant()
            1 -> TwoSearchFragment().newInstant()
            else -> OneSearchFragment().newInstant()
        }
    }

    //tab의 개수만큼 return
    override fun getCount(): Int = 2

    //tab의 이름 fragment마다 바꾸게 하기
    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0->"최근 검색어"
            1->"필터"
            else -> "렌즈"
        }
        return title
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}
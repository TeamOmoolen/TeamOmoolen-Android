package com.omoolen.omooroid.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivitySearchBinding
import com.omoolen.omooroid.search.fragment.SearchFragmentAdapter

class SearchActivity : AppCompatActivity() {
    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPageAdapter()

    }

    private fun clickEvents(){
        //TODO : 각 클릭 이벤트 걸기
        // 뒤로가기
        binding.ivBack.setOnClickListener {

        }
        //검색 이벤트 동작
        binding.clSearchTouch.setOnClickListener {
            //et_search에 들어온 값 서버에 get 요청하기
            //binding.etSearch.text
        }
    }

    private fun setPageAdapter(){
        val pagerAdapter = SearchFragmentAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.vp_search)
        pager.adapter = pagerAdapter
        val tab = findViewById<TabLayout>(R.id.tl_search)
        tab.setupWithViewPager(pager)
    }

}
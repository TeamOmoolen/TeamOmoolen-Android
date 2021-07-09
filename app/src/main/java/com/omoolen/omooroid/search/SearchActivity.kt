package com.omoolen.omooroid.search

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivitySearchBinding
import com.omoolen.omooroid.search.fragment.SearchFragmentAdapter
import com.omoolen.omooroid.search.fragment.one.OneSearchViewModel


class SearchActivity : AppCompatActivity() {
    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneSearchViewModel by viewModels() //위임초기화


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySearchBinding.inflate(layoutInflater)
        //viewModel.setRecentAdapter()
        setContentView(binding.root)
        setPageAdapter()
        clickEvents()
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
            Log.d("SEARCH_ACTIVITY","검색클릭")
            viewModel.addRecent(binding.etSearch.text.toString())
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
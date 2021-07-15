package com.omoolen.omooroid.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivitySearchBinding
import com.omoolen.omooroid.search.fragment.SearchFragmentAdapter
import com.omoolen.omooroid.search.search_result.SearchResultActivity


class SearchActivity : AppCompatActivity() {
    var imm : InputMethodManager? = null

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: SearchViewModel by viewModels() //위임초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPageAdapter()

        var isKeyboard : String? = intent?.getStringExtra("keyboard")
        if(isKeyboard != null)
            binding.etSearch.requestFocus()

        clickEvents()

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

    }

    fun hideKeyboard(v: View){
        if(v != null){
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun clickEvents() {
        //TODO : 각 클릭 이벤트 걸기
        // 뒤로가기
        binding.ivBack.setOnClickListener {
            Log.d("SEARCH", "ivBAck")
        }
        //검색 이벤트 동작
        binding.clSearchTouch.setOnClickListener {
            //et_search에 들어온 값 서버에 get 요청하기
            //viewModel.getSearch(binding.etSearch.text.toString())
            val intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra("keyword", binding.etSearch.text.toString())
            startActivity(intent)

            Log.d("SEARCH_ACTIVITY", "검색클릭")
            viewModel.addRecent(binding.etSearch.text.toString())
        }
    }

    //스와이프
    private fun setPageAdapter() {
        val pagerAdapter = SearchFragmentAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.vp_search)
        pager.adapter = pagerAdapter
        val tab = findViewById<TabLayout>(R.id.tl_search)
        tab.setupWithViewPager(pager)
    }

}
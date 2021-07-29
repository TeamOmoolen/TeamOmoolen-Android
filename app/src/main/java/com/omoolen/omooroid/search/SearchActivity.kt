package com.omoolen.omooroid.search

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
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


    }

    //현재 포커스 된 editText 외 다른 구역 터치 시 키보드 숨기기
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
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
            intent.putExtra("mode","keyword")
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
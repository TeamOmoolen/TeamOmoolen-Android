package com.omoolen.omooroid.search.search_result

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.ActivitySearchResultBinding
import com.omoolen.omooroid.util.VerticalItemDecorator

class SearchResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val searchResultViewModel : SearchResultViewModel by viewModels()

    private lateinit var searchResultListAdapter: SearchResultListAdapter
    private lateinit var  searchResultLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchResultListAdapter = SearchResultListAdapter()

        searchResultInit()



//        searchResultViewModel.setSearchResultList()
//        setSearchResultAdapter()
//        setSearchResultObserve()
//        searchrResultInit()
    }

    private fun searchResultInit() {
        binding.rvSearchResult.adapter = searchResultViewModel.setSearchResultAdapter()
        searchResultLayoutManager = GridLayoutManager(this, 3)

        searchResultLayoutManager = object : GridLayoutManager(this,3){
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = ((width - 20) / spanCount)
                return true
            }
        }
        binding.rvSearchResult.setHasFixedSize(true)
        binding.rvSearchResult.layoutManager = searchResultLayoutManager

        binding.rvSearchResult.addItemDecoration(VerticalItemDecorator(30,this))
    }

//    private fun searchResultInit() {
//
//    }
//
//    private fun setSearchResultAdapter() {
//        binding.rvSearchResult.adapter = SearchResultListAdapter()
//    }
//
//    // 어케 써야하지??
//    private fun setSearchResultObserve() {
//        searchResultViewModel.searchResultList.observe(this, Observer {
//            SearchResultActivity()
//        })
//    }
}
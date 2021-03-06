package com.omoolen.omooroid.search.search_result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.ActivitySearchResultBinding
import com.omoolen.omooroid.detail.DetailActivity
import com.omoolen.omooroid.home.fragments.one.networkApi.RecommendationBySituation
import com.omoolen.omooroid.home.fragments.one.recommend.SituationListAdapter
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.search.SearchActivity
import com.omoolen.omooroid.search.data.Item
import com.omoolen.omooroid.util.VerticalItemDecorator

class SearchResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val searchResultViewModel : SearchResultViewModel by viewModels()

    private lateinit var searchResultListAdapter: SearchResultListAdapter
    private lateinit var  searchResultLayoutManager: RecyclerView.LayoutManager
    private  var getKeyword : String? = null
    private var mode : String? = null
    var flag : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchResultListAdapter = SearchResultListAdapter()
        binding.rvSearchResult.adapter = searchResultListAdapter
        var totalCount : String = ""

        mode = intent.getStringExtra("mode")
        getKeyword = intent.getStringExtra("keyword")

        lateinit var filterList : ArrayList<Item>
        if(mode == "keyword"){
            if(getKeyword == null)
                getKeyword = "null"
            Log.d("SearchResult", getKeyword!!)


            searchResultViewModel.getSearch(getKeyword!!)
            searchResultAdapterInit()
        }
        else if(mode == "filter"){
            mode = intent.getStringExtra("mode")
            Log.d("TWOSEARCH","들어옴")

            filterList =
                intent.getSerializableExtra("filterList") as ArrayList<Item>
            Log.d("TWOSEARCH","들어옴")
            totalCount = intent.getStringExtra("totalItem").toString()
            Log.d("TWOSEARCH_TOTALCOUNT",totalCount.toString())


            searchResultListAdapter.setSearchResult(filterList)
            flag = true
        }



        if(flag == true){
            setSearchResultListObserve2(filterList, totalCount)
        } else {
            setSearchResultListObserve()
        }
        searchResultAdapterInit()


        binding.clSearchResultSort.setOnClickListener {
            val findSortPriceFragment = FindSortPriceFragment()

            findSortPriceFragment.setButtonClickListener(object : FindSortPriceFragment.OnButtonClickListener{
                override fun onLowPriceClicked() {
                    //여기서 정렬
                    Log.d("click", "low price")
                }

                override fun onHighPriceClicked() {
                    // 여기서 정렬
                    Log.d("click", "high price")
                }
            })
            findSortPriceFragment.show(supportFragmentManager, "CustomDialog3")
        }

        binding.clSearchResultTop.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("keyboard", "ok")
            startActivity(intent)
            finish()
        }


    }

    private fun setSearchResultListObserve() {
        searchResultViewModel.searchResultList.observe(this){
            searchResultList -> with(binding.rvSearchResult.adapter as SearchResultListAdapter){
                setSearchResult(searchResultList)
            }
        }

        searchResultViewModel.totalItem.observe(this){
            binding.tvSearchResultTotalNumber.text = "총 $it 개의 상품"
        }

    }

    private fun setSearchResultListObserve2(filter : ArrayList<Item>, totalCount:String) {
        searchResultViewModel.searchResultList.observe(this){
                searchResultList -> with(binding.rvSearchResult.adapter as SearchResultListAdapter){
            setSearchResult(filter)
        }
        }
        Log.d("###SEARCH_COUNT",totalCount)
        binding.tvSearchResultTotalNumber.text = "총 $totalCount 개의 상품"

    }



    private fun searchResultAdapterInit() {

        searchResultListAdapter.setItemClickListener(object: SearchResultListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val rbsi : Item = searchResultListAdapter.searchResultList.get(position)
                val intent = Intent(this@SearchResultActivity, DetailActivity::class.java)
                intent.putExtra("itemId", rbsi.id)
                startActivity(intent)
            }
        })

        //binding.rvSearchResult.adapter = searchResultListAdapter //
        searchResultLayoutManager = GridLayoutManager(this, 3)

        searchResultLayoutManager = object : GridLayoutManager(this, 3) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = ((width - 20) / spanCount)
                return true
            }
        }
        binding.rvSearchResult.setHasFixedSize(true)
        binding.rvSearchResult.layoutManager = searchResultLayoutManager

        binding.rvSearchResult.addItemDecoration(VerticalItemDecorator(30, this))
    }


}
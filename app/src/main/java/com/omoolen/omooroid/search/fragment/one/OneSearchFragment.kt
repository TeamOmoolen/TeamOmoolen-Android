package com.omoolen.omooroid.search.fragment.one

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.databinding.FragmentSearchOneBinding
import com.omoolen.omooroid.search.SearchViewModel
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularAdapter
import com.omoolen.omooroid.search.fragment.one.recycle.popular.PopularInfo
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentAdapter

class OneSearchFragment : Fragment() {
    private var _binding: FragmentSearchOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val popularViewModel: OneSearchViewModel by viewModels() //위임초기화
    private val viewModel: SearchViewModel by activityViewModels()

    private lateinit var mContext: Context

    lateinit var popularAdapter: PopularAdapter
    private lateinit var popularLayoutManager: RecyclerView.LayoutManager

    lateinit var recentAdapter: RecentAdapter
    private lateinit var recentLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchOneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mContext = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initRecentSearch(mContext)
        recentInit()
        observe()
        popularInit()
        setPopularData()
        clickEvents()
    }

    private fun clickEvents() {
        //전체삭제
        binding.tvDeleteAll.setOnClickListener {
            viewModel.deleteRecentAll()
        }
    }

    private var first: Int = 0
    private fun observe() {
        //최근검색어 반영
        viewModel.recentSearch.observe(viewLifecycleOwner, { recentSearch ->
            viewModel.updateRecent(mContext, recentSearch, recentAdapter)
            if (recentSearch.size == 0) {
                binding.tvNorecent.visibility = View.VISIBLE
                binding.rvRecent.visibility = View.GONE
            } else if (recentSearch.size > 0) {
                binding.tvNorecent.visibility = View.GONE
                binding.rvRecent.visibility = View.VISIBLE
            }
        })
    }

    //init recent recycler
    private fun recentInit() {
        binding.rvRecent.adapter = setRecentAdapter()
        recentLayoutManager = LinearLayoutManager(mContext)
        binding.rvRecent.layoutManager = recentLayoutManager
    }

    fun setRecentAdapter(): RecentAdapter {
        recentAdapter = RecentAdapter()
//        recentAdapter.recentList.addAll(
//            //TODO : sharedpreference에 있는 내용 저장하기
//            listOf<RecentInfo>(
//                RecentInfo(name = "오렌즈 시크리스 그레이"),
//                RecentInfo(name = "오렌즈 시크리스 그레이2"),
//                RecentInfo(name = "오렌즈 시크리스 그레이3")
//            )
//        )
        Log.d("ONESEARCHVIEWMODEL", "추가")

        recentAdapter.setItemClickListener(object : RecentAdapter.OnItemClickListener {
            override fun deleteRecentOnClick(v: View, position: Int) {
                viewModel.deleteRecent(position)
            }

            override fun searchOnClick(v: View, position: Int) {
                //TODO : 상세페이지로 이동
                //oneSearchViewModel.intentToDetail()
            }
        })
        return recentAdapter
    }

    //swipe view
    fun newInstant(): OneSearchFragment {
        val args = Bundle()
        val frag = OneSearchFragment()
        frag.arguments = args
        return frag
    }


    //init popular recycler
    private fun popularInit() {
        popularAdapter = PopularAdapter(requireContext())
        popularAdapter.popularList.addAll(
            listOf<PopularInfo>(
                PopularInfo(1, "1", "오렌즈 시크리스 그레이"),
                PopularInfo(2, "2", "오렌즈 시크리스 그레이2"),
                PopularInfo(3, "3", "오렌즈 시크리스 그레이3"),
                PopularInfo(4, "1", "오렌즈 시크리스 그레이"),
                PopularInfo(5, "2", "오렌즈 시크리스 그레이2"),
                PopularInfo(6, "3", "오렌즈 시크리스 그레이3"),
                PopularInfo(7, "1", "오렌즈 시크리스 그레이"),
                PopularInfo(8, "2", "오렌즈 시크리스 그레이2"),
                PopularInfo(9, "3", "오렌즈 시크리스 그레이3")
            )
        )
        popularAdapter.setItemClickListener(object : PopularAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }
        })
        binding.rvPopular.adapter = popularAdapter
        popularLayoutManager = LinearLayoutManager(mContext)
        binding.rvPopular.layoutManager = popularLayoutManager
    }

    private fun setPopularData() {
//        popularViewModel.popularSuccess.observe(viewLifecycleOwner) { success ->
//            if (success) {
//                Log.d("POPULAR", "들어옴")
//                popularAdapter.popularList.clear()
//                popularAdapter.popularList.addAll(popularViewModel.popularList)
//
//                popularAdapter.notifyDataSetChanged()
//            }
//        }
        popularViewModel.getPopularList()  //서버 통신

        popularViewModel.popularList.observe(viewLifecycleOwner, {
            Log.d("POPULAR", "들어옴")
            popularAdapter.popularList.clear()
            popularAdapter.popularList.addAll(it)
            popularAdapter.notifyDataSetChanged()
        })
    }

}
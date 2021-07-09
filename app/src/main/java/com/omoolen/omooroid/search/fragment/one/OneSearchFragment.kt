package com.omoolen.omooroid.search.fragment.one

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentOnboardOneBinding
import com.omoolen.omooroid.databinding.FragmentSearchOneBinding
import com.omoolen.omooroid.onboarding.fragments.one.OneOnboardViewModel
import com.omoolen.omooroid.util.HorizontalItemDecorator

class OneSearchFragment : Fragment() {
    private var _binding: FragmentSearchOneBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private val viewModel: OneSearchViewModel by viewModels() //위임초기화
    private lateinit var mContext: Context

    private lateinit var recentLayoutManager: RecyclerView.LayoutManager
    private lateinit var popularLayoutManager: RecyclerView.LayoutManager

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
        recentInit()
        popularInit()
        observe()
    }

    private fun clickEvents(){
        //전체삭제
        binding.tvDeleteAll.setOnClickListener {
            viewModel.deleteRecentAll()
        }

    }
    private fun observe(){
        //TODO : recentList fragment에서도 sharedpreference로 관리해야 함.
        //sharedpreference값으로도 사이즈 검사해서 처음에 visible invisible 처리하기
        viewModel.recentSearch.observe(viewLifecycleOwner, { recentList ->
            if(recentList.size == 0){
                binding.tvNorecent.visibility = View.GONE
                binding.rvRecent.visibility = View.VISIBLE
            }
            else{
                binding.tvNorecent.visibility = View.VISIBLE
                binding.rvRecent.visibility = View.GONE
            }
        })
    }

    //init recent recycler
    private fun recentInit() {
        binding.rvRecent.adapter = viewModel.setRecentAdapter()
        recentLayoutManager = LinearLayoutManager(mContext)
        binding.rvRecent.layoutManager = recentLayoutManager
    }
    //init popular recycler
    private fun popularInit() {
        binding.rvPopular.adapter = viewModel.setPopularAdapter()
        popularLayoutManager = LinearLayoutManager(mContext)
        binding.rvPopular.layoutManager = popularLayoutManager
    }
    //swipe view
    fun newInstant() : OneSearchFragment {
        val args = Bundle()
        val frag = OneSearchFragment()
        frag.arguments = args
        return frag
    }
}
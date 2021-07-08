package com.omoolen.omooroid.search.fragment.one

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun recentInit() {
        binding.rvRecent.adapter = viewModel.setRecentAdapter()
        recentLayoutManager = LinearLayoutManager(mContext)
        binding.rvRecent.layoutManager = recentLayoutManager
    }
    private fun popularInit() {
        binding.rvPopular.adapter = viewModel.setPopularAdapter()
        popularLayoutManager = LinearLayoutManager(mContext)
        binding.rvPopular.layoutManager = popularLayoutManager
    }

    fun newInstant() : OneSearchFragment {
        val args = Bundle()
        val frag = OneSearchFragment()
        frag.arguments = args
        return frag
    }
}
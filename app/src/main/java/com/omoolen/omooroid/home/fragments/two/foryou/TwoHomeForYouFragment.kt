package com.omoolen.omooroid.home.fragments.two.foryou

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeOneBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter

class TwoHomeForYouFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeForYouFragment()
    }

    private var _binding: FragmentHomeTwoForyouBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeForYouViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoForyouBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setForYouList()
        setForYouAdapter()
        setForYouObserve()

        return binding.root
    }

    private fun setForYouAdapter(){
        binding.rvFindForyou.adapter = RecommendListAdapter()
    }

    private fun setForYouObserve() {
        viewModel.foryouList.observe(viewLifecycleOwner) { foryouList ->
            with(binding.rvFindForyou.adapter as RecommendListAdapter) {
                setRecommend(foryouList)
            }
        }
    }
}
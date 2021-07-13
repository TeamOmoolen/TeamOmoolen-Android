package com.omoolen.omooroid.home.fragments.two.situation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoSituBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel

class TwoHomeSituFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeSituFragment()
    }

    private var _binding: FragmentHomeTwoSituBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeSituViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoSituBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setSituList()
        setSituAdapter()
        setSituObserve()

        return binding.root
    }

    private fun setSituAdapter(){
        binding.rvFindSituation.adapter = RecommendListAdapter()
    }

    private fun setSituObserve() {
        viewModel.situList.observe(viewLifecycleOwner) { situList ->
            with(binding.rvFindSituation.adapter as RecommendListAdapter) {
                setRecommend(situList)
            }
        }
    }

}
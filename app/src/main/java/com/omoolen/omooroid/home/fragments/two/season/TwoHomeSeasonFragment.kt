package com.omoolen.omooroid.home.fragments.two.season

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoSeasonBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel

class TwoHomeSeasonFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeSeasonFragment()
    }

    private var _binding: FragmentHomeTwoSeasonBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeSeasonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoSeasonBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setSeasonList()
        setSeasonAdapter()
        setSeasonObserve()

        return binding.root
    }

    private fun setSeasonAdapter(){
        binding.rvFindSeason.adapter = RecommendListAdapter()
    }

    private fun setSeasonObserve() {
        viewModel.seasonList.observe(viewLifecycleOwner) { seasonList ->
            with(binding.rvFindSeason.adapter as RecommendListAdapter) {
                setRecommend(seasonList)
            }
        }
    }
}
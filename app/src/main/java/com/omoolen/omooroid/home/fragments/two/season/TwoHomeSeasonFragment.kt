package com.omoolen.omooroid.home.fragments.two.season

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omoolen.omooroid.R
import com.omoolen.omooroid.databinding.FragmentHomeTwoForyouBinding
import com.omoolen.omooroid.databinding.FragmentHomeTwoSeasonBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.two.FindQuestionFragment
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator

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

        binding.ivFindQuestion4.setOnClickListener {
            val findSortPriceFragment = FindSortPriceFragment()

            findSortPriceFragment.setButtonClickListener(object: FindSortPriceFragment.OnButtonClickListener {
                override fun onLowPriceClicked() {
                    //여기서 정렬
                    Log.d("click", "low price")
                }

                override fun onHighPriceClicked() {
                    // 여기서 정렬
                    Log.d("click", "high price")
                }
            })
            findSortPriceFragment.show(childFragmentManager, "CustomDialog")
        }

        binding.ivFindQuestion4.setOnClickListener {
            val findQuestionFragment = FindQuestionFragment(4)
            findQuestionFragment.show(childFragmentManager, "CustomDialog2")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFindSeason.addItemDecoration(HorizontalItemDecorator(12,2, requireContext()))
        binding.rvFindSeason.addItemDecoration(VerticalItemDecorator(36, requireContext()))

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
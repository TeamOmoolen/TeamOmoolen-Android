package com.omoolen.omooroid.home.fragments.two.situation

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
import com.omoolen.omooroid.databinding.FragmentHomeTwoSituBinding
import com.omoolen.omooroid.home.fragments.one.recommend.RecommendListAdapter
import com.omoolen.omooroid.home.fragments.one.recommend.SituationListAdapter
import com.omoolen.omooroid.home.fragments.two.FindQuestionFragment
import com.omoolen.omooroid.home.fragments.two.FindSortPriceFragment
import com.omoolen.omooroid.home.fragments.two.TwoHomeViewModel
import com.omoolen.omooroid.home.fragments.two.foryou.TwoHomeForYouViewModel
import com.omoolen.omooroid.util.HorizontalItemDecorator
import com.omoolen.omooroid.util.VerticalItemDecorator

class TwoHomeSituFragment : Fragment() {

    companion object {
        fun newInstance() = TwoHomeSituFragment()
    }

    private var _binding: FragmentHomeTwoSituBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private val viewModel: TwoHomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTwoSituBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getSuggestData()
        setSituAdapter()
        setSituObserve()


        binding.ivSituationSort.setOnClickListener {
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
        binding.ivFindQuestion2.setOnClickListener {
            val findQuestionFragment = FindQuestionFragment(2)
            findQuestionFragment.show(childFragmentManager, "CustomDialog2")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFindSituation.addItemDecoration(HorizontalItemDecorator(12,2, requireContext()))
        binding.rvFindSituation.addItemDecoration(VerticalItemDecorator(36, requireContext()))

    }

    private fun setSituAdapter(){
        binding.rvFindSituation.adapter = SituationListAdapter()
    }

    private fun setSituObserve() {
        viewModel.forSituationList.observe(viewLifecycleOwner) { recommendList ->
            with(binding.rvFindSituation.adapter as SituationListAdapter) {
                setRecommend(recommendList)
            }
        }
    }

}